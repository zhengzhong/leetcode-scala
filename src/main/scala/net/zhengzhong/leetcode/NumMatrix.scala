package net.zhengzhong.leetcode

import math._

/**
 * 304. Range Sum Query 2D - Immutable
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 */
class NumMatrix(_matrix: Array[Array[Int]]) {

  val matrix: Array[Array[Int]] = _matrix
  val numRows: Int = matrix.length
  val numCols: Int = if (numRows > 0) matrix(0).length else 0
  val sum: Int = doSumRegion(0, 0, numRows - 1, numCols - 1)

  private def doSumRegion(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Int = {
    var sum = 0
    for {
      row <- fromRow to toRow
      col <- fromCol to toCol
    } sum += matrix(row)(col)
    sum
  }

  def sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int = {
    val regionSize = max(row2 - row1, 0) * max(col2 - col1, 0)
    if (regionSize * 2 < numRows * numCols) {
      doSumRegion(row1, col1, row2, col2)
    } else {
      val top = doSumRegion(0, 0, row1 - 1, numCols - 1)
      val bottom = doSumRegion(row2 + 1, 0, numRows - 1, numCols - 1)
      val left = doSumRegion(row1, 0, row2, col1 - 1)
      val right = doSumRegion(row1, col2 + 1, row2, numCols - 1)
      sum - top - bottom - left - right
    }
  }
}
