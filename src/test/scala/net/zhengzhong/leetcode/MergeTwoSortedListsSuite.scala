package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.MergeTwoSortedLists.mergeTwoLists
import net.zhengzhong.leetcode.util.ListNode
import org.junit.Assert.assertEquals
import org.junit.Test

class MergeTwoSortedListsSuite {

  @Test
  def test_merge_two_sorted_lists(): Unit = {
    assertEquals(
      mergeTwoLists(ListNode(1, 2, 4), ListNode(1, 3, 4)),
      ListNode(1, 1, 2, 3, 4, 4)
    )
  }
}
