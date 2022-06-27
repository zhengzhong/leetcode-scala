package net.zhengzhong.leetcode

/**
 * 189. Rotate Array
 * https://leetcode.com/problems/rotate-array/
 */
object RotateArray {

  @annotation.tailrec
  def rotateToLeft(nums: Array[Int], k: Int): Unit = {
    require(k < nums.length)
    if (k > 0) {
      var prev = nums(0)
      for (index <- nums.indices.reverse) {
        val temp = nums(index)
        nums(index) = prev
        prev = temp
      }
      rotateToLeft(nums, k - 1)
    }
  }

  @annotation.tailrec
  def rotateToRight(nums: Array[Int], k: Int): Unit = {
    require(k < nums.length)
    if (k > 0) {
      var prev = nums(0)
      for (i <- nums.indices) {
        val index = (i + 1) % nums.length
        val temp = nums(index)
        nums(index) = prev
        prev = temp
      }
      rotateToRight(nums, k - 1)
    }
  }

  @annotation.tailrec
  def rotate(nums: Array[Int], k: Int): Unit = {
    require(nums.length >= 1)

    if (k >= nums.length) rotate(nums, k % nums.length)
    else if (k > 0) {
      if (k <= nums.length / 2) {
        rotateToRight(nums, k)
      } else {
        rotateToLeft(nums, nums.length - k)
      }
    }
  }
}
