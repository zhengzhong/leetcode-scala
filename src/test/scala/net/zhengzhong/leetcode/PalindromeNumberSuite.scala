package net.zhengzhong.leetcode

import org.junit.Assert._
import org.junit.Test

class PalindromeNumberSuite {

  import PalindromeNumber._

  @Test
  def test_is_palindrome(): Unit = {
    assertTrue(isPalindrome(121))
    assertFalse(isPalindrome(-121))
    assertFalse(isPalindrome(10))
    assertTrue(isPalindrome(0))
    assertTrue(isPalindrome(7))
    assertTrue(isPalindrome(123454321))
    assertFalse(isPalindrome(123454320))
  }
}
