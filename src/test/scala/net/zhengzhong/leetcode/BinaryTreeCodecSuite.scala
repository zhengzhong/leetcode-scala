package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.util.TreeNode
import org.junit.Assert.assertEquals
import org.junit.Test

class BinaryTreeCodecSuite {
  val codec = new BinaryTreeCodec

  @Test
  def test_binary_tree_codec_with_null(): Unit = {
    assertEquals(codec.deserialize(""), null)
    assertEquals(codec.deserialize(null), null)
    assertEquals(codec.serialize(null), "")
  }

  @Test
  def test_binary_tree_codec(): Unit = {
    val data = "1,2,3,null,null,4,5"
    val tree = new TreeNode(
      1,
      new TreeNode(2),
      new TreeNode(3, new TreeNode(4), new TreeNode(5))
    )

    val decoded = codec.deserialize(data)
    assertEquals(tree, decoded)

    val encoded = codec.serialize(decoded)
    assertEquals(encoded, data)
  }

}
