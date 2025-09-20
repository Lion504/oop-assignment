import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * WiktionaryService - Handles integration with Wiktionary API to fetch word definitions.
 * Uses both the MediaWiki API and web scraping as fallback methods.
 */
public class WiktionaryService {
    private static final String WIKTIONARY_API_BASE = "https://en.wiktionary.org/api/rest_v1/page/definition/";
    private static final String WIKTIONARY_PARSE_API = "https://en.wiktionary.org/w/api.php";
    private static final int TIMEOUT_SECONDS = 10;

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public WiktionaryService() {
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Fetches word definition from Wiktionary asynchronously.
     *
     * @param word the word to look up
     * @return CompletableFuture containing the definition or error message
     */
    public CompletableFuture<String> getDefinitionAsync(String word) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Prioritize parse API with HTML scraping for comprehensive results
                String definition = fetchFromParseAPI(word);
                if (definition != null && !definition.isEmpty() &&
                    !definition.contains("not found") && !definition.contains("Unable to extract")) {
                    return definition;
                }

                // Fallback to REST API only if parse API fails completely
                String restDefinition = fetchFromRestAPI(word);
                if (restDefinition != null && !restDefinition.isEmpty()) {
                    return restDefinition;
                }

                return "Word '" + word + "' not found in Wiktionary";

            } catch (Exception e) {
                return "Error fetching definition: " + e.getMessage();
            }
        }).orTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
          .exceptionally(throwable -> "Timeout or error occurred while fetching definition from Wiktionary");
    }

    /**
     * Attempts to fetch definition using Wiktionary's REST API.
     */
    private String fetchFromRestAPI(String word) {
        try {
            String encodedWord = URLEncoder.encode(word.trim(), StandardCharsets.UTF_8);
            String url = WIKTIONARY_API_BASE + encodedWord;

            HttpGet request = new HttpGet(url);
            request.setHeader("User-Agent", "DictionaryApp/1.0 (Educational Project)");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                if (response.getCode() == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    return parseRestAPIResponse(responseBody);
                }
            }
        } catch (Exception e) {
            System.err.println("REST API failed for word '" + word + "': " + e.getMessage());
        }
        return null;
    }

    /**
     * Attempts to fetch definition using MediaWiki Parse API with HTML parsing.
     */
    private String fetchFromParseAPI(String word) {
        try {
            String encodedWord = URLEncoder.encode(word.trim(), StandardCharsets.UTF_8);
            // Get the full page content, not just section 0
            String url = WIKTIONARY_PARSE_API + "?action=parse&format=json&page=" + encodedWord +
                        "&prop=text&disabletoc=true";

            HttpGet request = new HttpGet(url);
            request.setHeader("User-Agent", "DictionaryApp/1.0 (Educational Project)");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                if (response.getCode() == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    return parseMediaWikiResponse(responseBody, word);
                }
            }
        } catch (Exception e) {
            System.err.println("Parse API failed for word '" + word + "': " + e.getMessage());
        }
        return "Definition not found in Wiktionary";
    }

    /**
     * Parses the REST API JSON response to extract definition.
     */
    private String parseRestAPIResponse(String jsonResponse) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode definitions = root.path("en");

            if (definitions.isArray() && definitions.size() > 0) {
                JsonNode firstDef = definitions.get(0);
                JsonNode defArray = firstDef.path("definitions");

                if (defArray.isArray() && defArray.size() > 0) {
                    String definition = defArray.get(0).path("definition").asText();
                    String partOfSpeech = firstDef.path("partOfSpeech").asText();

                    return formatDefinition(partOfSpeech, definition);
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing REST API response: " + e.getMessage());
        }
        return null;
    }

    /**
     * Parses MediaWiki API response and extracts definition from HTML.
     */
    private String parseMediaWikiResponse(String jsonResponse, String word) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            String html = root.path("parse").path("text").path("*").asText();

            if (html.isEmpty()) {
                return "Word '" + word + "' not found in Wiktionary";
            }

            // Parse HTML to extract definition
            Document doc = Jsoup.parse(html);

            // Remove unwanted elements but keep content
            doc.select("style, script, .NavFrame, .NavHead, .mw-collapsible, .metadata").remove();

            // Convert links to plain text before extracting definitions
            Elements links = doc.select("a[rel='mw:WikiLink']");
            for (Element link : links) {
                String linkText = link.text();
                if (!linkText.isEmpty()) {
                    link.replaceWith(new org.jsoup.nodes.TextNode(linkText));
                }
            }

            // Find comprehensive English definitions with part of speech
            String comprehensiveDefinition = extractComprehensiveDefinitions(doc, word);
            if (!comprehensiveDefinition.isEmpty()) {
                return comprehensiveDefinition;
            }

            // Fallback to simpler extraction
            Elements definitions = doc.select("ol li, dl dd, .definition");
            if (!definitions.isEmpty()) {
                return formatDefinitions(definitions, word);
            }

        } catch (Exception e) {
            System.err.println("Error parsing MediaWiki response: " + e.getMessage());
        }
        return "Unable to extract definition for '" + word + "' from Wiktionary";
    }


    /**
     * Formats multiple definitions into a readable format.
     */
    private String formatDefinitions(Elements definitions, String word) {
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (Element def : definitions) {
            String text = def.text();
            if (text.length() > 15 && !isUnwantedContent(text)) {
                String cleanDef = cleanAndFormatDefinition(text);
                if (!cleanDef.isEmpty()) {
                    if (count > 0) {
                        result.append("\n");
                    }
                    result.append("• ").append(cleanDef);
                    count++;
                    if (count >= 8) break; // Increased from 3 to 8 for more comprehensive results
                }
            }
        }

        return result.length() > 0 ? result.toString() : "Unable to extract definition for '" + word + "' from Wiktionary";
    }

    /**
     * Checks if content should be excluded from definitions.
     */
    private boolean isUnwantedContent(String text) {
        String lower = text.toLowerCase();
        return lower.contains("disambiguation") ||
               lower.contains("see also") ||
               lower.contains("category:") ||
               lower.contains("redirect") ||
               lower.contains("wikipedia") ||
               lower.contains("etymology") ||
               lower.contains("pronunciation") ||
               lower.contains("alternative forms") ||
               lower.startsWith("from ") ||
               lower.startsWith("see ") ||
               lower.startsWith("compare ") ||
               lower.startsWith("related to ") ||
               text.length() < 15;
    }

    /**
     * Extracts comprehensive definitions organized by part of speech, similar to Wiktionary website display.
     */
    private String extractComprehensiveDefinitions(Document doc, String word) {
        StringBuilder result = new StringBuilder();

        // Look for English section more broadly
        Elements headers = doc.select("h2, h3, h4");
        Element englishHeader = null;

        for (Element header : headers) {
            String headerText = header.text().toLowerCase();
            if ((headerText.contains("english") && !headerText.contains("old english") && !headerText.contains("middle english")) ||
                headerText.equals("english")) {
                englishHeader = header;
                break;
            }
        }

        if (englishHeader == null) {
            // Try to find definitions without explicit English header
            return extractDefinitionsFromEntirePage(doc, word);
        }

        // Find all content after English header until next major language section
        Element currentElement = englishHeader.nextElementSibling();
        String currentPartOfSpeech = "";
        int definitionCount = 0;
        boolean foundDefinitions = false;

        while (currentElement != null && definitionCount < 15) { // Reasonable limit
            String tagName = currentElement.tagName();
            String elementText = currentElement.text();

            // Stop if we hit another major language section
            if (tagName.equals("h2") && !elementText.toLowerCase().contains("english") &&
                !elementText.toLowerCase().contains("etymology") &&
                !elementText.toLowerCase().contains("pronunciation")) {
                break;
            }

            // Check for part of speech headers (h3, h4, h5)
            if (tagName.matches("h[345]")) {
                String posText = elementText.toLowerCase().trim();
                if (isPartOfSpeech(posText)) {
                    currentPartOfSpeech = capitalizeFirst(extractPartOfSpeech(posText));
                    if (result.length() > 0) {
                        result.append("\n\n");
                    }
                    result.append("=== ").append(currentPartOfSpeech).append(" ===\n");
                }
            }

            // Look for definition lists (ol, ul)
            if (tagName.equals("ol") || tagName.equals("ul")) {
                Elements listItems = currentElement.select("li");
                int defNum = 1;
                for (Element li : listItems) {
                    if (defNum > 6) break; // Max 6 definitions per part of speech

                    String defText = extractCoreDefinition(li);
                    if (!defText.isEmpty() && defText.length() > 10 && !isUnwantedContent(defText)) {
                        String cleanDef = cleanAndFormatDefinition(defText);
                        if (!cleanDef.isEmpty() && !isExampleOrCitation(cleanDef)) {
                            result.append(defNum).append(". ").append(cleanDef).append("\n");
                            definitionCount++;
                            foundDefinitions = true;
                            defNum++;
                        }
                    }
                }
            }

            currentElement = currentElement.nextElementSibling();
        }

        if (foundDefinitions) {
            return result.toString().trim();
        } else {
            return extractDefinitionsFromEntirePage(doc, word);
        }
    }

    /**
     * Extracts the core definition text from a list item, filtering out examples and citations.
     */
    private String extractCoreDefinition(Element listItem) {
        // Clone the element to avoid modifying the original
        Element clone = listItem.clone();

        // Remove example sentences, citations, and quotes
        clone.select("dl, .h-usage-example, .citation, .quotation, blockquote").remove();
        clone.select("ul.citations, .cited-source").remove();

        // Get the text content
        String text = clone.text();

        // Find the core definition by stopping at common separators that indicate examples
        String[] exampleMarkers = {
            " [from ", " [since ", " 1", " 2", " \"", " '", " —", " –",
            " (", " Synonym", " Hyponym", " Coordinate", " Example"
        };

        for (String marker : exampleMarkers) {
            int markerIndex = text.indexOf(marker);
            if (markerIndex > 20) { // Only cut if there's substantial definition before the marker
                text = text.substring(0, markerIndex).trim();
                break;
            }
        }

        return text;
    }

    /**
     * Checks if the text appears to be an example or citation rather than a definition.
     */
    private boolean isExampleOrCitation(String text) {
        String lower = text.toLowerCase();
        return lower.matches(".*\\b(19|20)\\d{2}\\b.*") ||  // Contains year (likely citation)
               lower.contains("example:") ||
               lower.contains("quote:") ||
               lower.contains("citation:") ||
               lower.contains("from ") ||
               lower.contains("see also") ||
               lower.contains("compare") ||
               lower.contains("contrast") ||
               lower.contains("synonym") ||
               lower.contains("antonym") ||
               lower.contains("coordinate term") ||
               lower.contains("hyponym") ||
               lower.contains("hypernym") ||
               lower.length() > 200; // Very long text is likely an example
    }

    /**
     * Fallback method to extract definitions from the entire page when structured parsing fails.
     */
    private String extractDefinitionsFromEntirePage(Document doc, String word) {
        StringBuilder result = new StringBuilder();

        // Look for any ordered lists that might contain definitions
        Elements allLists = doc.select("ol");
        int count = 0;

        for (Element list : allLists) {
            Elements items = list.select("li");
            for (Element item : items) {
                if (count >= 8) break; // Limit total fallback definitions

                String text = extractCoreDefinition(item);
                if (text.length() > 15 && text.length() < 150 &&
                    !isUnwantedContent(text) && !isExampleOrCitation(text)) {

                    String cleanDef = cleanAndFormatDefinition(text);
                    if (!cleanDef.isEmpty()) {
                        if (count > 0) result.append("\n");
                        result.append("• ").append(cleanDef);
                        count++;
                    }
                }
            }
        }

        return count > 0 ? result.toString() : "";
    }

    /**
     * Checks if a header text represents a part of speech.
     */
    private boolean isPartOfSpeech(String text) {
        String[] partsOfSpeech = {
            "noun", "verb", "adjective", "adverb", "pronoun", "preposition",
            "conjunction", "interjection", "article", "determiner", "numeral",
            "participle", "gerund", "infinitive", "proper noun", "abbreviation",
            "acronym", "initialism", "symbol", "letter", "phrase", "idiom"
        };

        for (String pos : partsOfSpeech) {
            if (text.contains(pos)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Extracts the actual part of speech from header text.
     */
    private String extractPartOfSpeech(String headerText) {
        String[] partsOfSpeech = {
            "noun", "verb", "adjective", "adverb", "pronoun", "preposition",
            "conjunction", "interjection", "article", "determiner", "numeral",
            "participle", "gerund", "infinitive", "proper noun", "abbreviation",
            "acronym", "initialism", "symbol", "letter", "phrase", "idiom"
        };

        for (String pos : partsOfSpeech) {
            if (headerText.contains(pos)) {
                return pos;
            }
        }
        return headerText; // Return original if no match found
    }

    /**
     * Cleans and formats definition text by removing unwanted elements and improving readability.
     */
    private String cleanAndFormatDefinition(String definition) {
        if (definition == null || definition.trim().isEmpty()) {
            return "";
        }

        // First pass - remove HTML-like content and references
        String cleaned = definition
                .replaceAll("<[^>]*>", "")                       // Remove any remaining HTML tags
                .replaceAll("\\[\\d+\\]", "")                    // Remove reference numbers [1], [2], etc.
                .replaceAll("\\{[^}]*\\}", "")                   // Remove template markup {like this}
                .replaceAll("\\.mw-parser-output[^\\s]*", "")    // Remove .mw-parser-output artifacts
                .replaceAll("\\.defdate[^\\s]*", "")             // Remove .defdate artifacts
                .replaceAll("\\babout=\"[^\"]*\"", "")           // Remove about attributes
                .replaceAll("\\btypeof=\"[^\"]*\"", "")          // Remove typeof attributes
                .replaceAll("\\brel=\"[^\"]*\"", "")             // Remove rel attributes
                .replaceAll("\\bhref=\"[^\"]*\"", "")            // Remove href attributes
                .replaceAll("\\btitle=\"[^\"]*\"", "")           // Remove title attributes
                .replaceAll("\\bclass=\"[^\"]*\"", "")           // Remove class attributes
                .replaceAll("\\bid=\"[^\"]*\"", "")              // Remove id attributes
                .replaceAll("\\bspan\\b", "")                    // Remove span word remnants
                .replaceAll("\\bdiv\\b", "")                     // Remove div word remnants
                .replaceAll("\\bsee\\s+also\\b.*", "")          // Remove "see also" sections
                .replaceAll("\\bcategory:.*", "")               // Remove category references
                .replaceAll("^[\\s•-]+", "")                    // Remove leading bullets or dashes
                .replaceAll("\\s+", " ")                        // Normalize whitespace
                .replaceAll("\\s*;\\s*$", "")                   // Remove trailing semicolons
                .replaceAll("\\s*\\.$", "")                     // Remove trailing periods
                .trim();

        // Second pass - clean up any remaining artifacts and CSS class remnants
        cleaned = cleaned
                .replaceAll("\\.[a-zA-Z][a-zA-Z0-9-_]*", "")     // Remove any remaining CSS class names
                .replaceAll("\\s*,\\s*", ", ")                   // Fix comma spacing
                .replaceAll("\\s*;\\s*", "; ")                   // Fix semicolon spacing
                .replaceAll("\\s+", " ")                        // Final whitespace normalization
                .trim();

        return cleaned;
    }

    /**
     * Formats definition with part of speech and improved readability.
     */
    private String formatDefinition(String partOfSpeech, String definition) {
        String cleanDef = cleanAndFormatDefinition(definition);

        if (partOfSpeech != null && !partOfSpeech.isEmpty() && !partOfSpeech.equals("null")) {
            return String.format("(%s) %s", capitalizeFirst(partOfSpeech), cleanDef);
        }
        return cleanDef;
    }

    /**
     * Capitalizes the first letter of a string.
     */
    private String capitalizeFirst(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Closes the HTTP client resources.
     */
    public void close() {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing HTTP client: " + e.getMessage());
        }
    }
}
