package net.zhengzhong.leetcode

object MedianOfTwoSortedArrays {

  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    require(nums1.length + nums2.length >= 1)

    @annotation.tailrec
    def forward(index: Int, index1: Int, index2: Int): Int = {
      require(index1 < nums1.length || index2 < nums2.length)
      val (num, nextIndex1, nextIndex2) =
        if (index1 == nums1.length) {
          (nums2(index2), index1, index2 + 1)
        } else if (index2 == nums2.length) {
          (nums1(index1), index1 + 1, index2)
        } else if (nums1(index1) <= nums2(index2)) {
          (nums1(index1), index1 + 1, index2)
        } else {
          (nums2(index2), index1, index2 + 1)
        }
      if (index == 0) num
      else forward(index - 1, nextIndex1, nextIndex2)
    }

    @annotation.tailrec
    def backward(index: Int, index1: Int, index2: Int): Int = {
      require(index1 >= 0 || index2 >= 0)
      val (num, nextIndex1, nextIndex2) =
        if (index1 < 0) {
          (nums2(index2), index1, index2 - 1)
        } else if (index2 < 0) {
          (nums1(index1), index1 - 1, index2)
        } else if (nums1(index1) >= nums2(index2)) {
          (nums1(index1), index1 - 1, index2)
        } else {
          (nums2(index2), index1, index2 - 1)
        }
      if (index == 0) num
      else backward(index - 1, nextIndex1, nextIndex2)
    }

    val count = (nums1.length + nums2.length + 1) / 2
    val num1 = forward(count - 1, 0, 0)
    val num2 = backward(count - 1, nums1.length - 1, nums2.length - 1)
    (num1 + num2) / 2.0
  }
}
