package net.zhengzhong.leetcode

/**
 * 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
 * https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
 */
object MaxAreaOfCake {

  def maxArea(h: Int, w: Int, horizontalCuts: Array[Int], verticalCuts: Array[Int]): Int = {
    java.util.Arrays.sort(horizontalCuts)
    java.util.Arrays.sort(verticalCuts)

    def maxDistance(cuts: Array[Int], size: Int): Int = {
      (-1 until cuts.length).map { index =>
        val currentCut = if (index == -1) 0 else cuts(index)
        val nextCut = if (index == cuts.length - 1) size else cuts(index + 1)
        assert(nextCut > currentCut)
        nextCut - currentCut
      }.max
    }

    val maxHorizontalDist = BigInt(maxDistance(horizontalCuts, h))
    val maxVerticalDist = BigInt(maxDistance(verticalCuts, w))
    ((maxHorizontalDist * maxVerticalDist) % (math.pow(10, 9) + 7).toInt).toInt
  }
}
