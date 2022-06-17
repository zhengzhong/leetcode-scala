package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.LongestPalindromicSubstring.longestPalindrome
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.Test

class LongestPalindromicSubstringSuite {

  @Test
  def test_longest_palindrome(): Unit = {
    assertTrue(List("bab", "aba") contains longestPalindrome("babad"))
    assertTrue(List("a", "b", "c", "d", "e") contains longestPalindrome("abcde"))
    assertEquals("", longestPalindrome(""))
    assertEquals("x", longestPalindrome("x"))
    assertEquals("xx", longestPalindrome("xx"))
    assertEquals("bb", longestPalindrome("cbbd"))
    assertEquals("abcdedcba", longestPalindrome("abcdedcba"))
    assertEquals("abcdeedcba", longestPalindrome("abcdeedcba"))
  }
}
