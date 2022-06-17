package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.RotateList.rotateRight
import org.junit.Test
import net.zhengzhong.leetcode.util.ListNode
import org.junit.Assert.assertEquals

class RotateListSuite {

  @Test
  def test_rotate_right(): Unit = {
    assertEquals(rotateRight(ListNode(), 5), ListNode())
    assertEquals(rotateRight(ListNode(1, 2, 3, 4, 5), 0), ListNode(1, 2, 3, 4, 5))
    assertEquals(rotateRight(ListNode(1, 2, 3, 4, 5), 5), ListNode(1, 2, 3, 4, 5))
    assertEquals(rotateRight(ListNode(1, 2, 3, 4, 5), 2), ListNode(4, 5, 1, 2, 3))
    assertEquals(rotateRight(ListNode(1, 2, 3, 4, 5), 7), ListNode(4, 5, 1, 2, 3))
    assertEquals(rotateRight(ListNode(0, 1, 2), 4), ListNode(2, 0, 1))
  }
}
