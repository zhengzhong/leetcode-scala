package net.zhengzhong.leetcode

/**
 * 51. N-Queens II
 * https://leetcode.com/problems/n-queens-ii/
 */
object NQueens2 {
  def totalNQueens(n: Int): Int = {
    require(n >= 1)

    def canAttack(pos1: (Int, Int), pos2: (Int, Int)): Boolean =
      pos1._1 == pos2._1 || pos1._2 == pos2._2 || math.abs(pos1._1 - pos2._1) == math.abs(pos1._2 - pos2._2)

    def countFromRow(positions: List[(Int, Int)], row: Int): Int = {
      if (row == n - 1) positions.size
      else {
        val (candidates, remaining) = positions.partition(_._1 == row)
        val subCounts = for {
          candidate <- candidates
          subPositions = remaining.filter(!canAttack(candidate, _))
          subCount = countFromRow(subPositions, row + 1)
        } yield subCount
        subCounts.sum
      }
    }

    val allPositions = (for {
      i <- 0 until n
      j <- 0 until n
    } yield (i, j)).toList
    countFromRow(allPositions, 0)
  }
}
