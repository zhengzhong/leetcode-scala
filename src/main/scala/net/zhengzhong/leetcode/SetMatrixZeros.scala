package net.zhengzhong.leetcode

/**
 * 73. Set Matrix Zeroes
 * https://leetcode.com/problems/set-matrix-zeroes/
 */
object SetMatrixZeros {

  def setZeroes(matrix: Array[Array[Int]]): Unit = {
    assert(matrix.length > 0 && matrix(0).length > 0)

    // We use row #0 and column #0 of the matrix as flags indicating if we should set the
    // corresponding row or column to zeros. Because `matrix(0)(0)` is ambiguous -- it could
    // indicate either row #0 or column #0, we define:
    //
    // - `shouldSetRow0ToZeros == true` indicates that *row #0* should be set to zeros.
    // - `matrix(0)(0) == 0` indicates that *column #0* should be set to zeros.

    val shouldSetRow0ToZeros = matrix(0).contains(0)
    for {
      row <- matrix.indices
      col <- matrix(0).indices
      if matrix(row)(col) == 0
    } {
      if (row != 0) {
        matrix(row)(0) = 0
      }
      matrix(0)(col) = 0
    }

    for (row <- matrix.indices if row != 0 && matrix(row)(0) == 0) {
      matrix(row).indices.foreach { col => matrix(row)(col) = 0 }
    }
    for (col <- matrix(0).indices if matrix(0)(col) == 0) {
      matrix.indices.foreach { row => matrix(row)(col) = 0 }
    }
    if (shouldSetRow0ToZeros) {
      matrix(0).indices.foreach { col => matrix(0)(col) = 0 }
    }
  }
}
