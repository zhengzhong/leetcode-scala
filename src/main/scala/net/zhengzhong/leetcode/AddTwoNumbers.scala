package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.util.ListNode

/**
 * 2. Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 */
object AddTwoNumbers {

  def addTwoNumbers(n1: ListNode, n2: ListNode): ListNode = {

    def addWithCarry(n1: ListNode, n2: ListNode, carry: Int = 0): ListNode = {
      if (n1 == null && n2 == null) {
        if (carry == 0) null
        else new ListNode(carry)
      } else if (n1 == null || n2 == null) {
        val remaining = if (n1 == null) n2 else n1
        if (carry == 0) {
          remaining
        } else {
          val currentSum = remaining.x + carry
          val digit = currentSum % 10
          val newCarry = currentSum / 10
          new ListNode(digit, addWithCarry(null, remaining.next, newCarry))
        }
      } else {
        val currentSum = n1.x + n2.x + carry
        val digit = currentSum % 10
        val newCarry = currentSum / 10
        new ListNode(digit, addWithCarry(n1.next, n2.next, newCarry))
      }
    }

    addWithCarry(n1, n2)
  }
}