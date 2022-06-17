package net.zhengzhong.leetcode

import org.junit.Assert.assertEquals
import org.junit.Test

class LongestCommonPrefixSuite {

  import LongestCommonPrefix._

  @Test
  def test_longest_common_prefix(): Unit = {
    val simpleTestCases = Seq(
      (Array("flower", "flow", "flight"), "fl"),
      (Array("dog", "racecar", "car"), ""),
      (Array("abcdefg", "abcd", "abcdef"), "abcd"),
    )
    for ((strs, expected) <- simpleTestCases) {
      assertEquals(longestCommonPrefix(strs), expected)
    }
  }
}
