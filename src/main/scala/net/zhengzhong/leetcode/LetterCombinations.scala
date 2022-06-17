package net.zhengzhong.leetcode

/**
 * 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
object LetterCombinations {
  def letterCombinations(digits: String): List[String] = {
    val numberToLetters = Map(
      '2' -> "abc",
      '3' -> "def",
      '4' -> "ghi",
      '5' -> "jkl",
      '6' -> "mno",
      '7' -> "pqrs",
      '8' -> "tuv",
      '9' -> "wxyz",
    )

    def loop(lettersList: List[String]): List[String] = lettersList match {
      case Nil =>
        // NOTE: This is the case when `lettersList` is empty.
        List()
      case letters :: Nil =>
        // NOTE: This is the recursion stop condition when `lettersList` is not empty.
        letters.map(_.toString).toList
      case letters :: tail =>
        for {
          letter <- letters.toList
          suffix <- loop(tail)
        } yield s"$letter$suffix"
    }

    loop(digits.map(numberToLetters(_)).toList)
  }
}
