package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.util.TreeNode

/**
 * 968. Binary Tree Cameras
 * https://leetcode.com/problems/binary-tree-cameras/
 */
object BinaryTreeCameras {

  // What is the minimal number of cameras we need to place to:
  //
  // - cover all the children, but not necessarily this node;
  // - cover the while subtree, including this node and all its children;
  // - cover the while subtree, and with a camera placed on this node.
  case class CameraCount(coverChildren: Int,
                         coverSubtree: Int,
                         coverSubtreeWithCamera: Int) {
    require(coverChildren <= coverSubtree && coverSubtree <= coverSubtreeWithCamera)
  }

  def countCamera(node: TreeNode): CameraCount = {
    require(node != null)

    // If this is a leaf node...
    if (node.left == null && node.right == null) {
      CameraCount(
        coverChildren = 0,
        coverSubtree = 1,
        coverSubtreeWithCamera = 1,
      )
    }

    // If this node has one single child node...
    else if (node.left == null || node.right == null) {
      val child = if (node.left == null) node.right else node.left
      val childCount = countCamera(child)
      CameraCount(
        coverChildren = Seq(
          childCount.coverChildren + 1,
          childCount.coverSubtree,
          childCount.coverSubtreeWithCamera,
        ).min,
        coverSubtree = Seq(
          childCount.coverChildren + 1,
          childCount.coverSubtree + 1,
          childCount.coverSubtreeWithCamera,
        ).min,
        coverSubtreeWithCamera = childCount.coverChildren + 1,
      )
    }

    // If this node has two child nodes...
    else {
      val leftCount = countCamera(node.left)
      val rightCount = countCamera(node.right)
      CameraCount(
        coverChildren = Seq(
          leftCount.coverSubtree + rightCount.coverSubtree,
          leftCount.coverChildren + rightCount.coverChildren + 1,
        ).min,
        coverSubtree = Seq(
          leftCount.coverSubtreeWithCamera + rightCount.coverSubtree,
          leftCount.coverSubtree + rightCount.coverSubtreeWithCamera,
          leftCount.coverChildren + rightCount.coverChildren + 1,
        ).min,
        coverSubtreeWithCamera = leftCount.coverChildren + rightCount.coverChildren + 1,
      )
    }
  }

  def minCameraCover(root: TreeNode): Int = {
    if (root == null) 0
    else countCamera(root).coverSubtree
  }
}
