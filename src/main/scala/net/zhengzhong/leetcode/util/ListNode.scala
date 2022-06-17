package net.zhengzhong.leetcode.util

class ListNode(_x: Int = 0, _next: ListNode = null) {
  var x: Int = _x
  var next: ListNode = _next

  override def equals(obj: Any): Boolean = obj match {
    case node: ListNode => x == node.x && next == node.next
    case _ => false
  }

  def mkString(sep: String): String = {

    @annotation.tailrec
    def loop(node: ListNode, acc: String): String = {
      if (node == null) s"$acc${sep}∅"
      else loop(node.next, s"$acc$sep${node.x}")
    }

    loop(next, s"$x")
  }

  override def toString: String = mkString(" → ")
}

object ListNode {

  def apply(xs: Int*): ListNode = xs match {
    case Seq() => null
    case head +: tail => new ListNode(head, apply(tail: _*))
  }
}
