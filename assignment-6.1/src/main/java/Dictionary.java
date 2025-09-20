/**
 * Dictionary class (Model) - Wiktionary-only implementation.
 * Directly uses Wiktionary as the sole data source without any local caching.
 */
public class Dictionary {
    private final WiktionaryService wiktionaryService;

    /**
     * Constructor initializes the dictionary with Wiktionary service only.
     */
    public Dictionary() {
        this.wiktionaryService = new WiktionaryService();
    }

    /**
     * Searches for a word asynchronously in Wiktionary.
     *
     * @param word the word to search for
     * @param callback callback to handle the result
     */
    public void searchWordAsync(String word, SearchCallback callback) {
        if (word == null || word.trim().isEmpty()) {
            callback.onError(new WordNotFoundException("Word cannot be empty or null"));
            return;
        }

        // Delegate to the WiktionaryService async method directly
        wiktionaryService.getDefinitionAsync(word)
            .thenAccept(wiktionaryMeaning -> {
                if (wiktionaryMeaning != null &&
                    !wiktionaryMeaning.startsWith("Error") &&
                    !wiktionaryMeaning.startsWith("Timeout") &&
                    !wiktionaryMeaning.contains("not found")) {

                    callback.onSuccess(wiktionaryMeaning);
                } else {
                    callback.onError(new WordNotFoundException("Word '" + word + "' not found in Wiktionary"));
                }
            })
            .exceptionally(throwable -> {
                callback.onError(new WordNotFoundException("Error searching for '" + word + "': " + throwable.getMessage()));
                return null;
            });
    }

    /**
     * Checks if Wiktionary integration is enabled (always true).
     *
     * @return true (always uses Wiktionary)
     */
    public boolean isWiktionaryEnabled() {
        return true;
    }

    /**
     * Gets statistics about the dictionary usage.
     *
     * @return dictionary statistics
     */
    public DictionaryStats getStats() {
        return new DictionaryStats();
    }

    /**
     * Closes resources (Wiktionary service).
     */
    public void close() {
        if (wiktionaryService != null) {
            wiktionaryService.close();
        }
    }

    /**
     * Callback interface for async search operations.
     */
    public interface SearchCallback {
        void onSuccess(String meaning);
        void onError(WordNotFoundException error);
    }

    /**
     * Statistics class for dictionary information.
     */
    public static class DictionaryStats {
        public DictionaryStats() {
        }

        @Override
        public String toString() {
            return "Wiktionary-powered dictionary (live lookup)";
        }
    }
}

/**
 * Custom exception for when a word is not found in the dictionary.
 */
class WordNotFoundException extends Exception {
    public WordNotFoundException(String message) {
        super(message);
    }
}
