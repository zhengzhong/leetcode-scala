package net.zhengzhong.leetcode

/**
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
object LongestPalindromicSubstring {

  def longestPalindrome(s: String): String = {

    def extractPalindrome(left: Int, right: Int): String = {
      if (left < 0 || right >= s.length || s(left) != s(right)) ""
      else {
        val maxSteps = math.min(left, s.length - 1 - right)
        // We can take at least one offset which is 0, because `s(left) == s(right)`
        val maxOffset = (0 to maxSteps).takeWhile { offset =>
          s(left - offset) == s(right + offset)
        }.last
        s.substring(left - maxOffset, right + maxOffset + 1)
      }
    }

    def longestString(strs: String*): String = {
      assert(strs.nonEmpty)
      strs.reduce { (s1, s2) => if (s1.length >= s2.length) s1 else s2 }
    }

    @annotation.tailrec
    def findLongestPalindrome(mid: Int, offset: Int, candidate: String): String = {
      val index = mid + offset
      if (index < 0 || index >= s.length) candidate
      else {
        val palindrome1 = extractPalindrome(index, index)
        val palindrome2 = extractPalindrome(index, index + 1)
        val nextCandidate = longestString(candidate, palindrome1, palindrome2)
        // Offset sequence should go left then right: 0, -1, +1, -2, +2, ...
        val nextOffset = {
          if (offset == 0) -1
          else if (offset < 0) -offset
          else -(offset + 1)
        }
        findLongestPalindrome(mid, nextOffset, nextCandidate)
      }
    }

    if (s.isEmpty) ""
    else if (s.length == 1) s
    else {
      val mid = s.length / 2
      findLongestPalindrome(mid, 0, "")
    }
  }
}
