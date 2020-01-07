import org.junit.Test;

import static org.junit.Assert.*;

public class OffByNTest {
    static CharacterComparator cc = new OffByN(2);
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testEqualChars() {
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("ac", cc));
        assertTrue(palindrome.isPalindrome("abc", cc));
        assertTrue(palindrome.isPalindrome("abdc", cc));
        assertFalse(palindrome.isPalindrome("aa", cc));
        assertFalse(palindrome.isPalindrome("aba", cc));
    }
}