package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.KthFactorOfN.kthFactor
import org.junit.Assert.assertEquals
import org.junit.Test

class KthFactorOfNSuite {

  @Test
  def test_kth_factor(): Unit = {
    assertEquals(3, kthFactor(12, 3))
    assertEquals(7, kthFactor(7, 2))
    assertEquals(-1, kthFactor(4, 4))
  }
}
