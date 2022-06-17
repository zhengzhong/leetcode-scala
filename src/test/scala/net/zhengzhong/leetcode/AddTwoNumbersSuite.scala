package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.AddTwoNumbers.addTwoNumbers
import net.zhengzhong.leetcode.util.ListNode
import org.junit.Assert.assertEquals
import org.junit.Test

class AddTwoNumbersSuite {

  private def mkNumber(value: Int): ListNode = {
    if (value < 10) new ListNode(value)
    else new ListNode(value % 10, mkNumber(value / 10))
  }

  @annotation.tailrec
  private def numberToInt(number: ListNode, multiplier: Int = 1, acc: Int = 0): Int = {
    if (number == null) acc
    else numberToInt(number.next, multiplier * 10, acc + number.x * multiplier)
  }

  @Test
  def test_add_two_numbers(): Unit = {
    assertEquals(numberToInt(addTwoNumbers(mkNumber(342), mkNumber(465))), 342 + 465)
    assertEquals(numberToInt(addTwoNumbers(mkNumber(0), mkNumber(0))), 0)
    assertEquals(numberToInt(addTwoNumbers(mkNumber(9999999), mkNumber(9999))), 9999999 + 9999)
  }
}
