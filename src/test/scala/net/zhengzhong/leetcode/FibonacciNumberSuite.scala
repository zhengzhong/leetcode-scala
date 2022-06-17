package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.FibonacciNumber.fib
import org.junit.Assert.assertEquals
import org.junit.Test

class FibonacciNumberSuite {

  @Test
  def test_fib(): Unit = {
    assertEquals(0, fib(0))
    assertEquals(1, fib(1))
    assertEquals(1, fib(2))
    assertEquals(2, fib(3))
    assertEquals(3, fib(4))
    assertEquals(55, fib(10))
  }
}
