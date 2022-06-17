package net.zhengzhong.leetcode

import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.Test

class NQueensSuite {

  import NQueens._

  private def isSolved(board: List[String], n: Int): Boolean = {

    @annotation.tailrec
    def parse(board: List[String], row: Int = 0, acc: List[Position] = List()): Solution = board match {
      case Nil => acc
      case head :: tail =>
        val col = head.indexOf('Q')
        parse(tail, row + 1, (row, col) :: acc)
    }

    @annotation.tailrec
    def check(queens: List[Position]): Boolean = queens match {
      case Nil => true
      case queen :: tail =>
        if (tail.exists(canAttack(queen, _))) false
        else check(tail)
    }

    if (board.size != n) false
    else check(parse(board).sortBy(_._1))
  }

  @Test
  def test_solve_n_queens(): Unit = {
    val output1Q = solveNQueens(1)
    assertEquals(output1Q.size, 1)
    assertTrue(output1Q.forall(isSolved(_, 1)))

    val output4Q = solveNQueens(4)
    assertEquals(output4Q.size, 2)
    assertTrue(output4Q.forall(isSolved(_, 4)))

    val output9Q = solveNQueens(9)
    assertEquals(output9Q.size, 352)
    assertTrue(output9Q.forall(isSolved(_, 9)))
  }
}
