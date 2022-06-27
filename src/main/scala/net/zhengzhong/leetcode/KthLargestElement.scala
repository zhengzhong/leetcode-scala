package net.zhengzhong.leetcode

/**
 * 215. Kth Largest Element in an Array
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
object KthLargestElement {
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    val queue = collection.mutable.PriorityQueue.empty[Int].reverse
    for (num <- nums) {
      queue.enqueue(num)
      if (queue.size > k) queue.dequeue()
    }
    queue.dequeue()
  }
}
