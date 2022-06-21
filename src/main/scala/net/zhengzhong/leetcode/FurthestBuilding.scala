package net.zhengzhong.leetcode



/**
 * 1642. Furthest Building You Can Reach
 * https://leetcode.com/problems/furthest-building-you-can-reach/
 */
object FurthestBuilding {

  // Solution #1: Priority Queue ------------------------------------------------------------------

  /**
   * The instinct behind this solution is that: If the furthest building index that we could reach
   * is `N`, then for all the `N` gaps, we should use ladders for the biggest ones, while using
   * bricks to fill in the rest of them.
   */
  def furthestBuildingUsingPriorityQueue(heights: Array[Int], bricks: Int, ladders: Int): Int = {

    import scala.collection.mutable

    @annotation.tailrec
    def moveOn(index: Int, bigGaps: mutable.PriorityQueue[Int], sumOfSmallGaps: Int): Int = {
      require(index >= 0 && index < heights.length)
      if (index == heights.length - 1) index
      else {
        val gap = heights(index + 1) - heights(index)
        if (gap <= 0) {
          // We can go directly to the next building.
          moveOn(index + 1, bigGaps, sumOfSmallGaps)
        } else if (bigGaps.size < ladders) {
          // We still have more ladders: Let's put this gap as a candidate one for using a ladder.
          bigGaps.enqueue(gap)
          moveOn(index + 1, bigGaps, sumOfSmallGaps)
        } else {
          // We've used up all ladders: Let's dequeue the smallest gap and try to fill it using bricks.
          bigGaps.enqueue(gap)
          val smallestGap = bigGaps.dequeue()
          val updatedSumOfSmallGaps = sumOfSmallGaps + smallestGap
          // If we don't have enough bricks to fill in the smallest gap, we have to stop here.
          // Otherwise, we go ahead.
          if (updatedSumOfSmallGaps > bricks) index
          else moveOn(index + 1, bigGaps, updatedSumOfSmallGaps)
        }
      }
    }

    moveOn(0, mutable.PriorityQueue.empty[Int].reverse, 0)
  }

  // Solution #2: Recursion (Memory Limit Exceeded) -----------------------------------------------
  // NOTE: This solution is not optimal.

  def furthestBuildingUsingRecursion(heights: Array[Int], bricks: Int, ladders: Int): Int = {

    def solve(index: Int, bricks: Int, ladders: Int): Int = {
      require(index >= 0 && index < heights.length)
      if (index == heights.length - 1) index
      else {
        val gap = heights(index + 1) - heights(index)
        if (gap <= 0) solve(index + 1, bricks, ladders)
        else if (bricks < gap && ladders == 0) index
        else if (bricks < gap && ladders >= 1) solve(index + 1, bricks, ladders - 1)
        else if (bricks >= gap && ladders == 0) solve(index + 1, bricks - gap, ladders)
        else {
          assert(bricks >= gap && ladders >= 1)
          // We can either use bricks or use a ladder.
          val usingBricks = solve(index + 1, bricks - gap, ladders)
          val usingLadder = solve(index + 1, bricks, ladders - 1)
          usingBricks max usingLadder
        }
      }
    }

    solve(0, bricks, ladders)
  }

  // ----------------------------------------------------------------------------------------------

  def furthestBuilding(heights: Array[Int], bricks: Int, ladders: Int): Int = {
    furthestBuildingUsingPriorityQueue(heights, bricks, ladders)
  }
}
