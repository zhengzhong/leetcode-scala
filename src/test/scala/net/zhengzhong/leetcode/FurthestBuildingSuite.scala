package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.FurthestBuilding.furthestBuilding
import org.junit.Assert.assertEquals
import org.junit.Test

class FurthestBuildingSuite {

  @Test
  def test_furthest_building(): Unit = {
    assertEquals(4, furthestBuilding(Array(4, 2, 7, 6, 9, 14, 12), bricks = 5, ladders = 1))
    assertEquals(7, furthestBuilding(Array(4, 12, 2, 7, 3, 18, 20, 3, 19), bricks = 10, ladders = 2))
    assertEquals(3, furthestBuilding(Array(14, 3, 19, 3), bricks = 17, ladders = 0))
    assertEquals(0, furthestBuilding(Array(1, 10, 9, 8), bricks = 8, ladders = 0))
  }
}
