import org.junit.Test;
import static org.junit.Assert.*;

public class PalindromeCheckerTest {
    @Test
    public void testSimplePalindrome() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("radar"));
        assertTrue(checker.isPalindrome("level"));
    }

    @Test
    public void testPalindromeWithSpacesAndPunctuation() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("A man, a plan, a canal, Panama"));
        assertTrue(checker.isPalindrome("Was it a car or a cat I saw?"));
    }

    @Test
    public void testNonPalindrome() {
        PalindromeChecker checker = new PalindromeChecker();
        assertFalse(checker.isPalindrome("hello"));
        assertFalse(checker.isPalindrome("openai"));
    }

    @Test
    public void testEmptyAndSingleChar() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome(""));
        assertTrue(checker.isPalindrome("a"));
    }

    @Test
    public void testPalindromeWithNumbers() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("12321"));
        assertFalse(checker.isPalindrome("12345"));
    }
}

