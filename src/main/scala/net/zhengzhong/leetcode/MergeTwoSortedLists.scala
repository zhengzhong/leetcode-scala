package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.util.ListNode

/**
 * 21. Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
object MergeTwoSortedLists {

  def mergeTwoLists(list1: ListNode, list2: ListNode): ListNode = {
    if (list1 == null) list2
    else if (list2 == null) list1
    else {
      val (first, second) = if (list1.x < list2.x) (list1, list2) else (list2, list1)
      new ListNode(first.x, mergeTwoLists(first.next, second))
    }
  }
}
