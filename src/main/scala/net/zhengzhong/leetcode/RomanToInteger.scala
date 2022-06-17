package net.zhengzhong.leetcode

/**
 * 13. Roman to Integer
 * https://leetcode.com/problems/roman-to-integer/
 */
object RomanToInteger {
  def romanToInt(s: String): Int = {

    @annotation.tailrec
    def loop(chars: List[Char], acc: Int): Int = chars match {
      case Nil => acc
      case 'M' :: tail => loop(tail, acc + 1000)
      case 'D' :: tail => loop(tail, acc + 500)
      case 'C' :: 'M' :: tail => loop(tail, acc + 900)
      case 'C' :: 'D' :: tail => loop(tail, acc + 400)
      case 'C' :: tail => loop(tail, acc + 100)
      case 'L' :: tail => loop(tail, acc + 50)
      case 'X' :: 'C' :: tail => loop(tail, acc + 90)
      case 'X' :: 'L' :: tail => loop(tail, acc + 40)
      case 'X' :: tail => loop(tail, acc + 10)
      case 'V' :: tail => loop(tail, acc + 5)
      case 'I' :: 'X' :: tail => loop(tail, acc + 9)
      case 'I' :: 'V' :: tail => loop(tail, acc + 4)
      case 'I' :: tail => loop(tail, acc + 1)
      case c => {
        println(s"Illegal character: $c")
        0
      }
    }

    loop(s.toList, 0)
  }
}
