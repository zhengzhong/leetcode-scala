package net.zhengzhong.leetcode

/**
 * 37. Sudoku Solver
 * https://leetcode.com/problems/sudoku-solver/
 */
object SudokuSolver {

  // Common utilities -----------------------------------------------------------------------------

  case class Position(row: Int, col: Int) {
    def conflictsWith(pos: Position): Boolean = {
      row == pos.row || col == pos.col || (row / 3 == pos.row / 3 && col / 3 == pos.col / 3)
    }
  }

  def emptyPositions(board: Array[Array[Char]]): LazyList[Position] = {
    for {
      row <- (0 until 9).to(LazyList)
      col <- 0 until 9
      if board(row)(col) == '.'
    } yield Position(row, col)
  }

  def findCandidates(board: Array[Array[Char]], pos: Position): Set[Char] = {
    val usedInRow = (0 until 9).map(board(pos.row)(_)).filter(_ != '.').toSet
    val usedInCol = (0 until 9).map(board(_)(pos.col)).filter(_ != '.').toSet
    val usedInBox = (
      for {
        rowOffset <- 0 until 3
        colOffset <- 0 until 3
      } yield board(pos.row / 3 * 3 + rowOffset)(pos.col / 3 * 3 + colOffset)
      ).filter(_ != '.').toSet
    val used = usedInRow ++ usedInCol ++ usedInBox
    ('1' to '9').toSet -- used
  }


  // Solution #1: Brute force ---------------------------------------------------------------------

  def solveSudokuWithBruteForce(board: Array[Array[Char]]): Unit = {
    require(board.length == 9 && board(0).length == 9)

    def solve(board: Array[Array[Char]]): Boolean = emptyPositions(board).headOption match {
      case None => true
      case Some(pos) =>
        val candidates = findCandidates(board, pos)
        if (candidates.isEmpty) false
        else {
          val isSolved = candidates.exists { digit =>
            board(pos.row)(pos.col) = digit
            solve(board) // Recursive call.
          }
          // If we cannot solve it with any of the candidates, restore to its original state.
          if (!isSolved) {
            board(pos.row)(pos.col) = '.'
          }
          isSolved
        }
    }

    val isSolved = solve(board)
    if (!isSolved) {
      throw new RuntimeException("Cannot solve this Sudoku!")
    }
  }


  // Solution #2: Immutable, slightly optimized ---------------------------------------------------

  class Sudoku(val board: Array[Array[Char]], val holes: Map[Position, Set[Char]], val filled: Map[Position, Char]) {
    require(board.length == 9 && board(0).length == 9)

    def isDone: Boolean = holes.isEmpty

    def isDead: Boolean = holes.nonEmpty && holes.exists(_._2.isEmpty)

    /**
     * Return the easiest hole, which is the one that has the least candidates.
     */
    def easiestHole: (Position, Set[Char]) = {
      assert(holes.nonEmpty)
      holes.reduce { (hole1, hole2) =>
        if (hole1._2.size < hole2._2.size) hole1
        else hole2
      }
    }

    /**
     * Fill in a hole, and return a new `Sudoku` object.
     */
    def fill(pos: Position, digit: Char): Sudoku = {
      val updatedHoles = (holes - pos).map {
        case (holePos, candidates) =>
          if (holePos.conflictsWith(pos)) holePos -> (candidates - digit)
          else holePos -> candidates
      }
      val updatedFilled = filled + (pos -> digit)
      new Sudoku(board, updatedHoles, updatedFilled)
    }
  }

  def solveSudokuOptimized(board: Array[Array[Char]]): Unit = {

    def solve(sudoku: Sudoku): Option[Sudoku] = {
      if (sudoku.isDone) Some(sudoku)
      else if (sudoku.isDead) None
      else {
        val (pos, candidates) = sudoku.easiestHole
        val solutions: LazyList[Sudoku] = for {
          candidate <- candidates.to(LazyList)
          nextSudoku = sudoku.fill(pos, candidate)
          solution <- solve(nextSudoku) // Recursive call.
        } yield solution
        solutions.headOption
      }
    }

    val holes = emptyPositions(board).map { pos => pos -> findCandidates(board, pos) }.toMap
    val sudoku = new Sudoku(board, holes, Map.empty)
    solve(sudoku) match {
      case None =>
        throw new RuntimeException("Cannot find a solution to this Sudoku!")
      case Some(solution) =>
        // We mutate the board here.
        solution.filled.foreach {
          case (Position(row, col), digit) => board(row)(col) = digit
        }
    }
  }


  // ----------------------------------------------------------------------------------------------

  def solveSudoku(board: Array[Array[Char]]): Unit = solveSudokuOptimized(board)
}
