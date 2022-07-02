package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.MaxAreaOfCake.maxArea
import org.junit.Assert.assertEquals
import org.junit.Test

class MaxAreaOfCakeSuite {

  @Test
  def test_max_area(): Unit = {
    assertEquals(4, maxArea(5, 4, Array(1, 2, 4), Array(1, 3)))
    assertEquals(6, maxArea(5, 4, Array(3, 1), Array(1)))
    assertEquals(9, maxArea(5, 4, Array(3), Array(3)))
    // Test overflow.
    assertEquals(81,  maxArea(1000000000, 1000000000, Array(2), Array(2)))
  }
}
