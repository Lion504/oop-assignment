public class PalindromeChecker {
    /**
     * Checks if the given string is a palindrome, ignoring spaces, punctuation, and capitalization.
     */
    public boolean isPalindrome(String str) {
        if (str == null) return false;
        // Remove non-alphanumeric characters and convert to lowercase
        // the meaning is to match any character that is NOT (^) a letter (A-Z, a-z) or a digit (0-9)
        String cleaned = str.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        // the start and end of the string
        int left = 0, right = cleaned.length() - 1;
        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

