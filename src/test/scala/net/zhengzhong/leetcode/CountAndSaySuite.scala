package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.CountAndSay.countAndSay
import org.junit.Assert.assertEquals
import org.junit.Test

class CountAndSaySuite {

  @Test
  def test_binary_tree_codec_with_null(): Unit = {
    assertEquals("1", countAndSay(1))
    assertEquals("1211", countAndSay(4))
    assertEquals("1113213211", countAndSay(8))
  }
}
