package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.BinaryTreeCameras.minCameraCover
import net.zhengzhong.leetcode.util.TreeNode
import org.junit.Assert.assertEquals
import org.junit.Test

class BinaryTreeCamerasSuite {

  @Test
  def test_binary_tree_codec_with_null(): Unit = {
    assertEquals(1, minCameraCover(TreeNode.load(0, 0, null, 0, 0)))
    assertEquals(2, minCameraCover(TreeNode.load(0, 0, null, 0, null, 0, null, null, 0)))
  }
}
