package net.zhengzhong.leetcode

/**
 * 167. Two Sum II - Input Array Is Sorted
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
object TwoSum2 {
  def twoSum(numbers: Array[Int], target: Int): Array[Int] = {

    @annotation.tailrec
    def binarySearch(begin: Int, end: Int, num: Int): Option[Int] = {
      if (begin >= end) None
      else {
        val mid = (begin + end) / 2
        if (num < numbers(mid)) binarySearch(begin, mid, num)
        else if (numbers(mid) < num) binarySearch(mid + 1, end, num)
        else Some(mid)
      }
    }

    @annotation.tailrec
    def findIndices(index1: Int = 0): Option[(Int, Int)] = {
      if (index1 >= numbers.length - 1) None
      else if (numbers(index1) * 2 > target) None
      else {
        binarySearch(index1 + 1, numbers.length, target - numbers(index1)) match {
          case None => findIndices(index1 + 1)
          case Some(index2) => Some((index1, index2))
        }
      }
    }

    findIndices() match {
      case Some((index1, index2)) => Array(index1 + 1, index2 + 1)
      case None => Array()
    }
  }
}
