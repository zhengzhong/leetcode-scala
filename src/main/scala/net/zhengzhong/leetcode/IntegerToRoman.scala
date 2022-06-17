package net.zhengzhong.leetcode

/**
 * 12. Integer to Roman
 * https://leetcode.com/problems/integer-to-roman/
 */
object IntegerToRoman {
  def intToRoman(num: Int): String = {
    require(1 <= num && num <= 3999)

    val romans = List(
      (1000, "M"),
      (900, "CM"),
      (500, "D"),
      (400, "CD"),
      (100, "C"),
      (90, "XC"),
      (50, "L"),
      (40, "XL"),
      (10, "X"),
      (9, "IX"),
      (5, "V"),
      (4, "IV"),
      (1, "I"),
    ).sortBy(_._1).reverse // It is important that the list is sorted in descending order.

    @annotation.tailrec
    def loop(n: Int, acc: String): String = {
      romans.find(_._1 <= n) match {
        case None => acc // `n` must be 0 then.
        case Some((value, symbol)) => loop(n % value, acc + symbol * (n / value))
      }
    }

    loop(num, "")
  }
}
