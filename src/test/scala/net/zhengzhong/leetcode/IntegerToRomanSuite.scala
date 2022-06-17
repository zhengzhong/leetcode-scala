package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.IntegerToRoman.intToRoman
import org.junit.Assert.assertEquals
import org.junit.Test

class IntegerToRomanSuite {
  @Test
  def test_binary_tree_codec_with_null(): Unit = {
    assertEquals("III", intToRoman(3))
    assertEquals("LVIII", intToRoman(58))
    assertEquals("MCMXCIV", intToRoman(1994))
  }
}
