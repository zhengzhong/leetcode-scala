package net.zhengzhong.leetcode

import org.junit.Assert.assertEquals
import org.junit.Test

class CountPrimesSuite {

  import CountPrimes._

  @Test
  def test_count_primes(): Unit = {
    assertEquals(countPrimes(0), 0)
    assertEquals(countPrimes(1), 0)
    assertEquals(countPrimes(2), 0)
    assertEquals(countPrimes(3), 1)
    assertEquals(countPrimes(4), 2)
    assertEquals(countPrimes(10), 4)
    assertEquals(countPrimes(1000), 168)
    assertEquals(countPrimes(499979), 41537)
  }
}
