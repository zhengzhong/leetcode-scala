package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.MedianOfTwoSortedArrays.findMedianSortedArrays
import org.junit.Assert.assertEquals
import org.junit.Test

class MedianOfTwoSortedArraysSuite {

  @Test
  def test_longest_palindrome(): Unit = {
    assertEquals(2.0000, findMedianSortedArrays(Array(1, 3), Array(2)), .0001)
    assertEquals(2.5000, findMedianSortedArrays(Array(1, 4), Array(2, 3)), .0001)
    assertEquals(2.0000, findMedianSortedArrays(Array(2), Array()), .0001)
    assertEquals(2.0000, findMedianSortedArrays(Array(), Array(2)), .0001)
    assertEquals(5.5000, findMedianSortedArrays(Array(1, 2, 3, 4, 5), Array(6, 7, 8, 9, 10)), .0001)
  }
}
