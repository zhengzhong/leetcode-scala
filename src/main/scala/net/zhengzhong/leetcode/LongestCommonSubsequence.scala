package net.zhengzhong.leetcode

/**
 * 1143. Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 */
object LongestCommonSubsequence {
  def longestCommonSubsequence(text1: String, text2: String): Int = {

    def calcFrom(index1: Int, index2: Int, memo: collection.mutable.Map[(Int, Int), Int]): Int =
      memo.getOrElseUpdate((index1, index2), {
        if (index1 == text1.length || index2 == text2.length)
          0
        else if (text1(index1) == text2(index2))
          1 + calcFrom(index1 + 1, index2 + 1, memo)
        else
          math.max(calcFrom(index1 + 1, index2, memo), calcFrom(index1, index2 + 1, memo))
      })

    calcFrom(0, 0, collection.mutable.Map.empty)
  }
}
