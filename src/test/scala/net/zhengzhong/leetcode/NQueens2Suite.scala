package net.zhengzhong.leetcode

import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.Test

class NQueens2Suite {

  import NQueens2._

  @Test
  def test_solve_n_queens(): Unit = {
    assertEquals(totalNQueens(1), 1)
    assertEquals(totalNQueens(4), 2)
    assertEquals(totalNQueens(9), 352)
  }
}
