package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.LongestCommonSubsequence.longestCommonSubsequence
import org.junit.Assert.assertEquals
import org.junit.Test

class LongestCommonSubsequenceSuite {
  @Test
  def test_min_distance(): Unit = {
    assertEquals(3, longestCommonSubsequence("abcde", "ace"))
    assertEquals(3, longestCommonSubsequence("abc", "abc"))
    assertEquals(0, longestCommonSubsequence("abc", "def"))
  }
}
