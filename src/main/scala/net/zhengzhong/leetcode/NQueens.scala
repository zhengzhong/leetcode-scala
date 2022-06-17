package net.zhengzhong.leetcode

/**
 * 51. N-Queens
 * https://leetcode.com/problems/n-queens/
 */
object NQueens {
  type Position = (Int, Int)
  type Solution = List[Position]

  def generatePositions(n: Int): List[Position] =
    (for {
      i <- 0 until n
      j <- 0 until n
    } yield (i, j)).toList

  def formatSolution(solution: Solution, n: Int): List[String] = {
    require(solution.size == n)
    solution.sortBy(_._1).zipWithIndex.map {
      case ((row, col), index) =>
        assert(row == index) // We should have exactly one queen at each row.
        val chars = (0 until n).toList.map { index => if (index == col) 'Q' else '.' }
        chars.mkString
    }
  }

  def canAttack(pos1: Position, pos2: Position): Boolean =
    pos1._1 == pos2._1 || pos1._2 == pos2._2 || math.abs(pos1._1 - pos2._1) == math.abs(pos1._2 - pos2._2)

  def solveNQueens(n: Int): List[List[String]] = {
    require(n >= 1)

    def solveFromRow(positions: List[Position], row: Int): List[Solution] = {
      if (row == n - 1) {
        positions.map(List(_))
      } else {
        val (candidates, remaining) = positions.partition(_._1 == row)
        for {
          candidate <- candidates
          subPositions = remaining.filter(!canAttack(candidate, _))
          subSolutions = solveFromRow(subPositions, row + 1) if subSolutions.nonEmpty
          subSolution <- subSolutions
        } yield candidate :: subSolution
      }
    }

    val solutions = solveFromRow(generatePositions(n), 0)
    solutions.map(formatSolution(_, n))
  }
}
