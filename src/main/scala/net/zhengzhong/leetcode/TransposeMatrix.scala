package net.zhengzhong.leetcode

/**
 * 867. Transpose Matrix
 * https://leetcode.com/problems/transpose-matrix/
 */
object TransposeMatrix {
  def transpose(matrix: Array[Array[Int]]): Array[Array[Int]] = {
    val rows = matrix.length
    val cols = matrix(0).length
    val result = for (i <- 0 until cols)
      yield {
        val rowNumbers = for (j <- 0 until rows) yield matrix(j)(i)
        rowNumbers.toArray
      }
    result.toArray
  }
}
