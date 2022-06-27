package net.zhengzhong.leetcode

/**
 * 1689. Partitioning Into Minimum Number Of Deci-Binary Numbers
 * https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/
 */
object MinPartitions {
  def minPartitions(n: String): Int = {
    require(n.nonEmpty)
    n.map(_.toString.toInt).max
  }
}
