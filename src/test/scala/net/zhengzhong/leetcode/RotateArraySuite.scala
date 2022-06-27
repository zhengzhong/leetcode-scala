package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.RotateArray.rotate
import org.junit.Assert.assertEquals
import org.junit.Test

class RotateArraySuite {

  @Test
  def test_rotate(): Unit = {

    def rotated(nums: Array[Int], k: Int): List[Int] = {
      rotate(nums, k)
      nums.toList
    }

    assertEquals(List(5, 6, 7, 1, 2, 3, 4), rotated(Array(1, 2, 3, 4, 5, 6, 7), 3))
    assertEquals(List(5, 6, 7, 1, 2, 3, 4), rotated(Array(1, 2, 3, 4, 5, 6, 7), 24))
    assertEquals(List(2, 3, 4, 5, 6, 7, 1), rotated(Array(1, 2, 3, 4, 5, 6, 7), 20))
    assertEquals(List(7, 1, 2, 3, 4, 5, 6), rotated(Array(1, 2, 3, 4, 5, 6, 7), 22))
    assertEquals(List(3, 99, -1, -100), rotated(Array(-1, -100, 3, 99), 2))
    assertEquals(List(-1, -100, 3, 99), rotated(Array(-1, -100, 3, 99), 0))
    assertEquals(List(-1, -100, 3, 99), rotated(Array(-1, -100, 3, 99), 8))
  }
}
