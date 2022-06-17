package net.zhengzhong.leetcode

import org.junit.Assert.assertEquals
import org.junit.Test

class SetMatrixZerosSuite {
  import SetMatrixZeros._

  private def flatten(matrix: Array[Array[Int]]): List[Int] = matrix.toList.flatMap(_.toList)

  @Test
  def test_set_zeros(): Unit = {
    val matrix1 = Array(
      Array(1, 1, 1),
      Array(1, 0, 1),
      Array(1, 1, 1),
    )
    setZeroes(matrix1)
    assertEquals(flatten(Array(
      Array(1, 0, 1),
      Array(0, 0, 0),
      Array(1, 0, 1),
    )), flatten(matrix1))

    val matrix2 = Array(
      Array(0, 1, 2, 0),
      Array(3, 4, 5, 2),
      Array(1, 3, 1, 5),
    )
    setZeroes(matrix2)
    assertEquals(flatten(Array(
      Array(0, 0, 0, 0),
      Array(0, 4, 5, 0),
      Array(0, 3, 1, 0),
    )), flatten(matrix2))

    val matrix3 = Array(
      Array( 1,  2,  3,  4),
      Array( 5,  0,  7,  8),
      Array( 0, 10, 11, 12),
      Array(13, 14, 15,  0),
    )
    setZeroes(matrix3)
    assertEquals(flatten(Array(
      Array(0, 0, 3, 0),
      Array(0, 0, 0, 0),
      Array(0, 0, 0, 0),
      Array(0, 0, 0, 0),
    )), flatten(matrix3))
  }
}
