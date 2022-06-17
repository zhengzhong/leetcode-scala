package net.zhengzhong.leetcode

/**
 * 28. Implement strStr()
 * https://leetcode.com/problems/implement-strstr/
 */
object ImplementStrStr {
  def strStr(haystack: String, needle: String): Int = {

    @annotation.tailrec
    def matchesNeedle(haystackIndex: Int, needleIndex: Int = 0): Boolean = {
      if (haystackIndex >= haystack.length || needleIndex >= needle.length) true
      else if (haystack.charAt(haystackIndex) != needle.charAt(needleIndex)) false
      else matchesNeedle(haystackIndex + 1, needleIndex + 1)
    }

    @annotation.tailrec
    def strStrFrom(haystackIndex: Int): Int = {
      if (matchesNeedle(haystackIndex)) haystackIndex
      else if (haystackIndex + needle.length >= haystack.length) -1
      else strStrFrom(haystackIndex + 1)
    }

    if (haystack.length >= needle.length) strStrFrom(0)
    else -1
  }
}
