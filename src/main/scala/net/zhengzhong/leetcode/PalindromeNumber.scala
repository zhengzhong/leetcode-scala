package net.zhengzhong.leetcode

/**
 * 9. Palindrome Number
 * https://leetcode.com/problems/palindrome-number/
 */
object PalindromeNumber {
  def isPalindrome(x: Int): Boolean = {
    @annotation.tailrec
    def reverse(num: Int, acc: Int = 0): Int = {
      if (num == 0) acc
      else reverse(num / 10, acc * 10 + num % 10)
    }

    if (x < 0) false
    else x == reverse(x)
  }
}
