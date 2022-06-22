package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.KthLargestElement.findKthLargest
import org.junit.Assert.assertEquals
import org.junit.Test

class KthLargestElementSuite {

  @Test
  def test_find_kth_largest(): Unit = {
    assertEquals(5, findKthLargest(Array(3, 2, 1, 5, 6, 4), 2))
    assertEquals(4, findKthLargest(Array(3, 2, 3, 1, 2, 4, 5, 5, 6), 4))
  }
}
