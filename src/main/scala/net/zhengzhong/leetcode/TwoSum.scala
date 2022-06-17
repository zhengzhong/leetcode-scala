package net.zhengzhong.leetcode

/**
 * 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 */
object TwoSum {

  type IndexedNumber = (Int, Int) // (num, index)

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val sortedPairs: Array[IndexedNumber] = nums.zipWithIndex.sorted

    @annotation.tailrec
    def loop(index: Int): Array[Int] = {
      if (index >= sortedPairs.length) {
        println("Exhausted nums array but could not find indexes of two sum.")
        Array()
      } else {
        val pair1 = sortedPairs(index)
        binarySearch(sortedPairs, index + 1, sortedPairs.length, target - pair1._1) match {
          case Some(pair2) => Array(pair1._2, pair2._2)
          case None => loop(index + 1)
        }
      }
    }

    loop(0)
  }

  @annotation.tailrec
  def binarySearch(sortedPair: Array[IndexedNumber], begin: Int, end: Int, target: Int): Option[IndexedNumber] = {
    if (begin >= end) None
    else {
      val mid = begin + (end - begin) / 2
      val pair = sortedPair(mid)
      if (target < pair._1) {
        binarySearch(sortedPair, begin, mid, target)
      } else if (pair._1 < target) {
        binarySearch(sortedPair, mid + 1, end, target)
      } else {
        // pair._1 == target
        Some(pair)
      }
    }
  }
}
