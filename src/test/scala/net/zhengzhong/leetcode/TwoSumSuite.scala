package net.zhengzhong.leetcode

import org.junit.Assert.assertEquals
import org.junit.Test

class TwoSumSuite {

  import TwoSum._

  @Test
  def test_two_sum(): Unit = {
    val simpleTestCases = Seq(
      (Array(2, 7, 11, 15), 9, Seq(0, 1)),
      (Array(3, 2, 4), 6, Seq(1, 2)),
      (Array(3, 3), 6, Seq(0, 1)),
    )
    for ((nums, target, expected) <- simpleTestCases) {
      val actual = twoSum(nums, target)
      assertEquals(actual.toSeq, expected)
    }
  }
}
