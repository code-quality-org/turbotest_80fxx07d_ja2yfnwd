package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

    private final StringUtils utils = new StringUtils();

    @Test
    void reverse() {
        assertEquals("olleh", utils.reverse("hello"));
    }

    @Test
    void reverseEmpty() {
        assertEquals("", utils.reverse(""));
    }

    @Test
    void isPalindromeTrue() {
        assertTrue(utils.isPalindrome("racecar"));
    }

    @Test
    void isPalindromeFalse() {
        assertFalse(utils.isPalindrome("hello"));
    }
}
