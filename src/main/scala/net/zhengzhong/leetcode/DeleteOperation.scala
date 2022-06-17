package net.zhengzhong.leetcode

/**
 * 583. Delete Operation for Two Strings
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 */
object DeleteOperation {
  def minDistance(word1: String, word2: String): Int = {

    def numStepsFrom(index1: Int, index2: Int, memo: collection.mutable.Map[(Int, Int), Int]): Int =
      memo.getOrElseUpdate((index1, index2), {
        if (index1 == word1.length)
          word2.length - index2
        else if (index2 == word2.length)
          word1.length - index1
        else if (word1(index1) == word2(index2))
          numStepsFrom(index1 + 1, index2 + 1, memo)
        else
          1 + math.min(
            numStepsFrom(index1 + 1, index2, memo),
            numStepsFrom(index1, index2 + 1, memo)
          )
      })

    numStepsFrom(0, 0, collection.mutable.Map.empty)
  }
}
