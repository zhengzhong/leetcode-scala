package net.zhengzhong.leetcode

/**
 * 1492. The kth Factor of n
 * https://leetcode.com/problems/the-kth-factor-of-n/
 */
object KthFactorOfN {
  def kthFactor(n: Int, k: Int): Int = {

    def factorsOfN(sortedNumbers: LazyList[Int]): LazyList[Int] = sortedNumbers match {
      case head #:: tail =>
        if (head > n) LazyList()
        else if (n % head == 0) head #:: factorsOfN(tail)
        else factorsOfN(tail)
      case _ =>
        throw new RuntimeException("Sorted numbers should be infinite.")
    }

    @annotation.tailrec
    def kthFactor(factors: LazyList[Int], kth: Int): Option[Int] = factors match {
      case LazyList() => None
      case head #:: tail => if (kth == 1) Some(head) else kthFactor(tail, kth - 1)
    }

    val factors = factorsOfN(LazyList.from(1))
    kthFactor(factors, k) match {
      case None => -1
      case Some(factor) => factor
    }
  }
}
