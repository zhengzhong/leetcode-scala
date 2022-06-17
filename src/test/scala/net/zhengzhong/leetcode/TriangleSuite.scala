package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.Triangle._
import org.junit.Assert.assertEquals
import org.junit.Test

class TriangleSuite {
  @Test
  def test_minimum_total(): Unit = {
    assertEquals(11, minimumTotal(List(
      List(2),
      List(3, 4),
      List(6, 5, 7),
      List(4, 1, 8, 3),
    )))
    assertEquals(6, minimumTotal(List(
      List(2),
      List(3, 4),
      List(6, 5, 1),
      List(4, 1, 8, -1),
    )))
    assertEquals(-10, minimumTotal(List(List(-10))))
  }
}
