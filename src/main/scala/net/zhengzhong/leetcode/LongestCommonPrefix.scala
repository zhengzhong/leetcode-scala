package net.zhengzhong.leetcode

/**
 * 14. Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 */
object LongestCommonPrefix {

  def longestCommonPrefix(strs: Array[String]): String = {

    val minSize = strs.map(_.length).min

    @annotation.tailrec
    def loop(index: Int, acc: String): String = {
      if (index == minSize) acc
      else {
        val charsAtIndex = strs.map(_(index))
        val char = charsAtIndex.head
        if (charsAtIndex.forall(_ == char)) loop(index + 1, acc + char)
        else acc
      }
    }

    loop(0, "")
  }
}
