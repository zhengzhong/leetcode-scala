package net.zhengzhong.leetcode

import org.junit.Assert.assertEquals
import org.junit.Test

class NumMatrixSuite {

  @Test
  def test_num_matrix(): Unit = {
    val numMatrix = new NumMatrix(Array(
      Array(3, 0, 1, 4, 2),
      Array(5, 6, 3, 2, 1),
      Array(1, 2, 0, 1, 5),
      Array(4, 1, 0, 1, 7),
      Array(1, 0, 3, 0, 5),
      Array(1, 1, 1, 1, 1),
    ))
    assertEquals(numMatrix.sumRegion(2, 1, 4, 3), 8)
    assertEquals(numMatrix.sumRegion(1, 1, 2, 2), 11)
    assertEquals(numMatrix.sumRegion(1, 2, 2, 4), 12)
    assertEquals(numMatrix.sumRegion(0, 0, 5, 3), 42)
  }
}
