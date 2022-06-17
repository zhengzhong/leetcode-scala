package net.zhengzhong.leetcode

import org.junit.Assert.assertEquals
import org.junit.Test

class RomanToIntegerSuite {

  import RomanToInteger._

  @Test
  def test_roman_to_int(): Unit = {
    assertEquals(romanToInt("III"), 3)
    assertEquals(romanToInt("LVIII"), 58)
    assertEquals(romanToInt("MCMXCIV"), 1994)
  }
}
