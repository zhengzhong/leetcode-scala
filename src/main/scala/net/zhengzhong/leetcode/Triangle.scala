package net.zhengzhong.leetcode

/**
 * 120. Triangle
 * https://leetcode.com/problems/triangle/
 */
object Triangle {

  def minimumTotalBottomUp(triangle: List[List[Int]]): Int = {

    @annotation.tailrec
    def buildUp(minTotals: List[List[Int]]): List[List[Int]] = {
      if (minTotals.size == triangle.size) minTotals
      else {
        val row = triangle.size - minTotals.size - 1
        val minTotalsAtRow = triangle(row).zipWithIndex.map {
          case (value, index) => value + math.min(minTotals.head(index), minTotals.head(index + 1))
        }
        buildUp(minTotalsAtRow :: minTotals)
      }
    }

    val minTotalTriangle = buildUp(List(triangle.last))
    minTotalTriangle.head.head
  }

  def minimumTotalTopDown(triangle: List[List[Int]]): Int = {

    def minimumTotalFrom(row: Int, col: Int, memo: collection.mutable.Map[(Int, Int), Int]): Int = {
      memo.get((row, col)) match {
        case Some(minTotal) => minTotal
        case None =>
          val top = triangle(row)(col)
          val minTotal =
            if (row == triangle.size - 1)
              top
            else
              top + math.min(
                minimumTotalFrom(row + 1, col, memo),
                minimumTotalFrom(row + 1, col + 1, memo)
              )
          memo((row, col)) = minTotal
          minTotal
      }
    }

    minimumTotalFrom(0, 0, collection.mutable.Map.empty)
  }

  val minimumTotal: List[List[Int]] => Int = minimumTotalBottomUp
}
