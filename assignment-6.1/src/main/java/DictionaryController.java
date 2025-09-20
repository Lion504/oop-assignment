import javafx.application.Platform;

/**
 * DictionaryController class (Controller) - Wiktionary-only implementation.
 * This class is designed to be independent of JavaFX components for better reusability.
 */
public class DictionaryController {
    private final Dictionary dictionary;

    /**
     * Constructor initializes the controller with a Wiktionary-only Dictionary instance.
     */
    public DictionaryController() {
        this.dictionary = new Dictionary(); // Wiktionary-only implementation
    }

    /**
     * Searches for a word asynchronously to avoid blocking the UI.
     * This is the preferred method for UI interactions.
     *
     * @param word the word to search for
     * @param callback callback to handle the result
     */
    public void searchForWordAsync(String word, AsyncSearchCallback callback) {
        // Validate input first
        if (word == null || word.trim().isEmpty()) {
            Platform.runLater(() -> callback.onResult(new SearchResult(false, "Please enter a word to search for.")));
            return;
        }

        // Use the dictionary's async search
        dictionary.searchWordAsync(word, new Dictionary.SearchCallback() {
            @Override
            public void onSuccess(String meaning) {
                Platform.runLater(() -> callback.onResult(new SearchResult(true, meaning)));
            }

            @Override
            public void onError(WordNotFoundException error) {
                Platform.runLater(() -> callback.onResult(new SearchResult(false, error.getMessage())));
            }
        });
    }

    /**
     * Gets comprehensive dictionary statistics.
     *
     * @return formatted statistics string
     */
    public String getDictionaryInfo() {
        Dictionary.DictionaryStats stats = dictionary.getStats();
        return stats.toString();
    }

    /**
     * Checks if Wiktionary integration is enabled (always true).
     *
     * @return true (always uses Wiktionary)
     */
    public boolean isWiktionaryEnabled() {
        return dictionary.isWiktionaryEnabled();
    }

    /**
     * Properly closes the dictionary resources.
     */
    public void shutdown() {
        dictionary.close();
    }

    /**
     * Callback interface for async search operations.
     */
    public interface AsyncSearchCallback {
        void onResult(SearchResult result);
    }

    /**
     * Result class for search operations.
     */
    public static class SearchResult {
        private final boolean success;
        private final String message;

        public SearchResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
