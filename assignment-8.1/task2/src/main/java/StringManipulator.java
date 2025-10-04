/**
 * StringManipulator class provides various string manipulation methods
 */
public class StringManipulator {

    /**
     * Concatenates (joins) two strings together
     *
     * @param str1 The first string
     * @param str2 The second string
     * @return The combined string
     */
    public String concatenate(String str1, String str2) {
        // Handle null values - if either string is null, treat it as empty
        if (str1 == null) str1 = "";
        if (str2 == null) str2 = "";

        return str1 + str2;
    }

    /**
     * Finds the length (number of characters) in a string
     *
     * @param str The string to measure
     * @return The number of characters in the string
     */
    public int findLength(String str) {
        // Handle null - if string is null, return 0
        if (str == null) {
            return 0;
        }

        return str.length();
    }

    /**
     * Converts all letters in a string to UPPERCASE
     *
     * @param str The string to convert
     * @return The string in uppercase
     */
    public String convertToUpperCase(String str) {
        // Handle null - if string is null, return empty string
        if (str == null) {
            return "";
        }

        return str.toUpperCase();
    }

    /**
     * Converts all letters in a string to lowercase
     *
     * @param str The string to convert
     * @return The string in lowercase
     */
    public String convertToLowerCase(String str) {
        // Handle null - if string is null, return empty string
        if (str == null) {
            return "";
        }

        return str.toLowerCase();
    }

    /**
     * Checks if a string contains another string (substring)
     *
     * @param str The main string to search in
     * @param subStr The substring to look for
     * @return true if substring is found, false otherwise
     */
    public boolean containsSubstring(String str, String subStr) {
        // Handle null values
        if (str == null || subStr == null) {
            return false;
        }

        return str.contains(subStr);
    }
}

