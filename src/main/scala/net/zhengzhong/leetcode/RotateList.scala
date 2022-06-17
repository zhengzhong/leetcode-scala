package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.util.ListNode

/**
 * 61. Rotate List
 * https://leetcode.com/problems/rotate-list/
 */
object RotateList {
  def rotateRight(head: ListNode, k: Int): ListNode = {

    @annotation.tailrec
    def listSize(node: ListNode, acc: Int = 0): Int =
      if (node == null) acc
      else listSize(node.next, acc + 1)

    @annotation.tailrec
    def split(node: ListNode, count: Int): ListNode = {
      assert(node != null && count >= 1)
      if (count == 1) {
        val secondHead = node.next
        node.next = null
        secondHead
      } else {
        split(node.next, count - 1)
      }
    }

    @annotation.tailrec
    def findLast(node: ListNode): ListNode = {
      assert(node != null)
      if (node.next == null) node
      else findLast(node.next)
    }

    if (head == null) null
    else {
      val size = listSize(head)
      val count = size - k % size
      if (count == size) head
      else {
        val newHead = split(head, count)
        val oldLast = findLast(newHead)
        oldLast.next = head
        newHead
      }
    }
  }
}
