package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.util.TreeNode

import scala.collection.immutable.Queue

/**
 * 297. Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
class BinaryTreeCodec {

  // Encodes a list of strings to a single string.
  def serialize(root: TreeNode): String = {

    @annotation.tailrec
    def tokenize(queue: Queue[TreeNode], tokens: Vector[String]): Vector[String] = {
      if (queue.isEmpty) tokens
      else {
        val (nextQueue: Queue[TreeNode], nextTokens: Vector[String]) = (queue.head.left, queue.head.right) match {
          case (null, null) => (
            queue.tail,
            tokens :+ "null" :+ "null",
          )
          case (leftNode, null) => (
            queue.tail :+ leftNode,
            tokens :+ s"${leftNode.value}" :+ "null",
          )
          case (null, rightNode) => (
            queue.tail :+ rightNode,
            tokens :+ "null" :+ s"${rightNode.value}",
          )
          case (leftNode, rightNode) => (
            queue.tail :+ leftNode :+ rightNode,
            tokens :+ s"${leftNode.value}" :+ s"${rightNode.value}",
          )
        }
        tokenize(nextQueue, nextTokens)
      }
    }

    if (root == null) ""
    else {
      val tokens = tokenize(Queue(root), Vector(s"${root.value}"))
      // The trailing "null"s could be ignored.
      tokens.reverse.dropWhile(_ == "null").reverse.mkString(",")
    }
  }

  // Decodes a single string to a list of strings.
  def deserialize(data: String): TreeNode = {

    @annotation.tailrec
    def assemble(queue: Queue[TreeNode], tokens: List[String]): Unit = {
      if (queue.isEmpty) {
        if (tokens.nonEmpty) {
          throw new IllegalArgumentException("Too many tokens to deserialize.")
        }
      } else {
        val node = queue.head
        tokens match {
          case Nil =>
            // No more tokens: All nodes in queue will have null children.
            ()
          case s :: Nil =>
            // One single token left: this should be the left child of the current node.
            // Note that trailing "null"s are optional and can be omitted.
            if (s != "null") {
              node.left = new TreeNode(s.toInt)
            }
          case "null" :: "null" :: tail =>
            assemble(queue.tail, tail)
          case s :: "null" :: tail =>
            node.left = new TreeNode(s.toInt)
            assemble(queue.tail :+ node.left, tail)
          case "null" :: s :: tail =>
            node.right = new TreeNode(s.toInt)
            assemble(queue.tail :+ node.right, tail)
          case s1 :: s2 :: tail =>
            node.left = new TreeNode(s1.toInt)
            node.right = new TreeNode(s2.toInt)
            assemble(queue.tail :+ node.left :+ node.right, tail)
        }
      }
    }

    if (data == null || data.isEmpty) null
    else {
      val tokens = data.split(",").toList
      assert(tokens.nonEmpty, "At least one token is required to rebuild a binary tree.")

      val root = new TreeNode(tokens.head.toInt)
      assemble(Queue(root), tokens.tail)
      root
    }
  }
}
