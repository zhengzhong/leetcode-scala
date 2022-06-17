package net.zhengzhong.leetcode

import org.junit.Assert.assertTrue
import org.junit.Test

class TransposeMatrixSuite {

  import TransposeMatrix._

  private def matrixEquals(m1: Array[Array[Int]], m2: Array[Array[Int]]): Boolean = {
    if (m1.length != m2.length) false
    else if (m1.length == 0) true
    else if (m1(0).length != m2(0).length) false
    else m1.flatMap(_.toSeq).toSeq == m2.flatMap(_.toSeq).toSeq
  }

  @Test
  def test_transpose_matrix(): Unit = {
    assertTrue(matrixEquals(
      transpose(Array(
        Array(1, 2, 3, 4),
        Array(5, 6, 7, 8),
        Array(9, 10, 11, 12),
      )),
      Array(
        Array(1, 5, 9),
        Array(2, 6, 10),
        Array(3, 7, 11),
        Array(4, 8, 12),
      )
    ))
  }
}
