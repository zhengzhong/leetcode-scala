package net.zhengzhong.leetcode.util

import scala.collection.immutable.Queue

class TreeNode(var _value: Int, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right

  override def equals(obj: Any): Boolean = obj match {
    case node: TreeNode => value == node.value && left == node.left && right == node.right
    case _ => false
  }
}

object TreeNode {

  /**
   * Load a sequence of tokens to a tree, using LeetCode's tree encoding.
   */
  def load(tokens: Integer*): TreeNode = {

    def treeNodeOrNull(value: Integer): TreeNode =
      if (value == null) null else new TreeNode(value.toInt)

    @annotation.tailrec
    def assemble(queue: Queue[TreeNode], tokens: Seq[Integer]): Unit = {
      if (queue.isEmpty && tokens.nonEmpty) {
        throw new IllegalArgumentException("Too many tokens.")
      }

      val node = queue.head
      tokens match {
        case Seq() =>
          () // No more tokens -- All nodes in queue will have no children.
        case Seq(x) =>
          node.left = treeNodeOrNull(x)
        case x0 +: x1 +: tokensLeft =>
          node.left = treeNodeOrNull(x0)
          node.right = treeNodeOrNull(x1)
          assemble(queue.tail ++ Seq(node.left, node.right).filter(_ != null), tokensLeft)
      }
    }

    tokens match {
      case Seq() => null
      case Seq(null) => null
      case v0 +: tokensLeft =>
        require(v0 != null, "Root node value cannot be null.")
        val root = new TreeNode(v0.toInt)
        assemble(Queue(root), tokensLeft)
        root
    }
  }
}
