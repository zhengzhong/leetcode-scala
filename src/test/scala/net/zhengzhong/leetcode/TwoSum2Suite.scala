package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.TwoSum2.twoSum
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.Test

class TwoSum2Suite {
  @Test
  def test_two_sum(): Unit = {
    assertTrue(twoSum(Array(2, 7, 11, 15), 9) sameElements Array(1, 2))
    assertTrue(twoSum(Array(2, 3, 4), 6) sameElements Array(1, 3))
    assertTrue(twoSum(Array(-1, 0), -1) sameElements Array(1, 2))
  }
}
