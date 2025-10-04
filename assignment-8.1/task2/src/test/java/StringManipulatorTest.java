import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for StringManipulator
 * This class tests all methods to ensure they work correctly
 */
@DisplayName("StringManipulator Test Suite")
public class StringManipulatorTest {

    // Create a StringManipulator object that we'll use in all tests
    private StringManipulator manipulator;

    /**
     * This method runs before each test
     * It creates a fresh StringManipulator object for each test
     */
    @BeforeEach
    public void setUp() {
        manipulator = new StringManipulator();
    }

    // ==================== Tests for concatenate() ====================

    @Test
    @DisplayName("Concatenate two normal strings")
    public void testConcatenateNormalStrings() {
        String result = manipulator.concatenate("Hello", "World");
        assertEquals("HelloWorld", result);
    }

    @Test
    @DisplayName("Concatenate with space")
    public void testConcatenateWithSpace() {
        String result = manipulator.concatenate("Hello ", "World");
        assertEquals("Hello World", result);
    }

    @Test
    @DisplayName("Concatenate empty strings")
    public void testConcatenateEmptyStrings() {
        String result = manipulator.concatenate("", "");
        assertEquals("", result);
    }

    @Test
    @DisplayName("Concatenate with one empty string")
    public void testConcatenateOneEmpty() {
        String result1 = manipulator.concatenate("Hello", "");
        assertEquals("Hello", result1);

        String result2 = manipulator.concatenate("", "World");
        assertEquals("World", result2);
    }

    @Test
    @DisplayName("Concatenate with null values")
    public void testConcatenateNull() {
        String result = manipulator.concatenate(null, "World");
        assertEquals("World", result);

        String result2 = manipulator.concatenate("Hello", null);
        assertEquals("Hello", result2);
    }

    // ==================== Tests for findLength() ====================

    @Test
    @DisplayName("Find length of normal string")
    public void testFindLengthNormalString() {
        int length = manipulator.findLength("Hello");
        assertEquals(5, length);
    }

    @Test
    @DisplayName("Find length of empty string")
    public void testFindLengthEmptyString() {
        int length = manipulator.findLength("");
        assertEquals(0, length);
    }

    @Test
    @DisplayName("Find length of string with spaces")
    public void testFindLengthWithSpaces() {
        int length = manipulator.findLength("Hello World");
        assertEquals(11, length); // Spaces count as characters!
    }

    @Test
    @DisplayName("Find length of null string")
    public void testFindLengthNull() {
        int length = manipulator.findLength(null);
        assertEquals(0, length);
    }

    @Test
    @DisplayName("Find length of string with numbers and symbols")
    public void testFindLengthWithNumbersAndSymbols() {
        int length = manipulator.findLength("Hello123!@#");
        assertEquals(11, length);
    }

    // ==================== Tests for convertToUpperCase() ====================

    @Test
    @DisplayName("Convert lowercase string to uppercase")
    public void testConvertToUpperCaseLowercase() {
        String result = manipulator.convertToUpperCase("hello");
        assertEquals("HELLO", result);
    }

    @Test
    @DisplayName("Convert mixed case string to uppercase")
    public void testConvertToUpperCaseMixed() {
        String result = manipulator.convertToUpperCase("HeLLo WoRLd");
        assertEquals("HELLO WORLD", result);
    }

    @Test
    @DisplayName("Convert already uppercase string")
    public void testConvertToUpperCaseAlreadyUpper() {
        String result = manipulator.convertToUpperCase("HELLO");
        assertEquals("HELLO", result);
    }

    @Test
    @DisplayName("Convert empty string to uppercase")
    public void testConvertToUpperCaseEmpty() {
        String result = manipulator.convertToUpperCase("");
        assertEquals("", result);
    }

    @Test
    @DisplayName("Convert null to uppercase")
    public void testConvertToUpperCaseNull() {
        String result = manipulator.convertToUpperCase(null);
        assertEquals("", result);
    }

    @Test
    @DisplayName("Convert string with numbers to uppercase")
    public void testConvertToUpperCaseWithNumbers() {
        String result = manipulator.convertToUpperCase("hello123");
        assertEquals("HELLO123", result); // Numbers stay the same
    }

    // ==================== Tests for convertToLowerCase() ====================

    @Test
    @DisplayName("Convert uppercase string to lowercase")
    public void testConvertToLowerCaseUppercase() {
        String result = manipulator.convertToLowerCase("HELLO");
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("Convert mixed case string to lowercase")
    public void testConvertToLowerCaseMixed() {
        String result = manipulator.convertToLowerCase("HeLLo WoRLd");
        assertEquals("hello world", result);
    }

    @Test
    @DisplayName("Convert already lowercase string")
    public void testConvertToLowerCaseAlreadyLower() {
        String result = manipulator.convertToLowerCase("hello");
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("Convert empty string to lowercase")
    public void testConvertToLowerCaseEmpty() {
        String result = manipulator.convertToLowerCase("");
        assertEquals("", result);
    }

    @Test
    @DisplayName("Convert null to lowercase")
    public void testConvertToLowerCaseNull() {
        String result = manipulator.convertToLowerCase(null);
        assertEquals("", result);
    }

    @Test
    @DisplayName("Convert string with numbers to lowercase")
    public void testConvertToLowerCaseWithNumbers() {
        String result = manipulator.convertToLowerCase("HELLO123");
        assertEquals("hello123", result); // Numbers stay the same
    }

    // ==================== Tests for containsSubstring() ====================

    @Test
    @DisplayName("Check if string contains substring - found")
    public void testContainsSubstringFound() {
        boolean result = manipulator.containsSubstring("Hello World", "World");
        assertTrue(result);
    }

    @Test
    @DisplayName("Check if string contains substring - not found")
    public void testContainsSubstringNotFound() {
        boolean result = manipulator.containsSubstring("Hello World", "Java");
        assertFalse(result);
    }

    @Test
    @DisplayName("Check if string contains substring - case sensitive")
    public void testContainsSubstringCaseSensitive() {
        boolean result1 = manipulator.containsSubstring("Hello World", "world");
        assertFalse(result1); // 'world' != 'World'

        boolean result2 = manipulator.containsSubstring("Hello World", "World");
        assertTrue(result2);
    }

    @Test
    @DisplayName("Check if string contains empty substring")
    public void testContainsSubstringEmpty() {
        boolean result = manipulator.containsSubstring("Hello", "");
        assertTrue(result); // Every string contains empty string!
    }

    @Test
    @DisplayName("Check if string contains itself")
    public void testContainsSubstringItself() {
        boolean result = manipulator.containsSubstring("Hello", "Hello");
        assertTrue(result);
    }

    @Test
    @DisplayName("Check if empty string contains substring")
    public void testContainsSubstringInEmpty() {
        boolean result = manipulator.containsSubstring("", "Hello");
        assertFalse(result);
    }

    @Test
    @DisplayName("Check substring with null values")
    public void testContainsSubstringNull() {
        boolean result1 = manipulator.containsSubstring(null, "Hello");
        assertFalse(result1);

        boolean result2 = manipulator.containsSubstring("Hello", null);
        assertFalse(result2);

        boolean result3 = manipulator.containsSubstring(null, null);
        assertFalse(result3);
    }

    @Test
    @DisplayName("Check substring at beginning")
    public void testContainsSubstringAtBeginning() {
        boolean result = manipulator.containsSubstring("Hello World", "Hello");
        assertTrue(result);
    }

    @Test
    @DisplayName("Check substring at end")
    public void testContainsSubstringAtEnd() {
        boolean result = manipulator.containsSubstring("Hello World", "World");
        assertTrue(result);
    }

    @Test
    @DisplayName("Check substring in middle")
    public void testContainsSubstringInMiddle() {
        boolean result = manipulator.containsSubstring("Hello World", "o W");
        assertTrue(result);
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Integration test: Concatenate and convert to uppercase")
    public void testConcatenateAndUppercase() {
        String concatenated = manipulator.concatenate("hello", "world");
        String uppercase = manipulator.convertToUpperCase(concatenated);
        assertEquals("HELLOWORLD", uppercase);
    }

    @Test
    @DisplayName("Integration test: Convert to lowercase and check length")
    public void testLowercaseAndLength() {
        String lowercase = manipulator.convertToLowerCase("HELLO");
        int length = manipulator.findLength(lowercase);
        assertEquals(5, length);
    }

    @Test
    @DisplayName("Integration test: Multiple operations")
    public void testMultipleOperations() {
        // Concatenate two strings
        String str1 = manipulator.concatenate("Hello", " ");
        String str2 = manipulator.concatenate(str1, "World");

        // Convert to uppercase
        String upper = manipulator.convertToUpperCase(str2);

        // Check if it contains a substring
        boolean contains = manipulator.containsSubstring(upper, "WORLD");


    }
}