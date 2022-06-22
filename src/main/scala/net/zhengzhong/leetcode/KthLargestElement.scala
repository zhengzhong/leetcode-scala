package net.zhengzhong.leetcode

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
