package net.zhengzhong.leetcode

/**
 * 38. Count and Say
 * https://leetcode.com/problems/count-and-say/
 */
object CountAndSay {

  def countAndSay(n: Int): String = {
    require(1 <= n && n <= 30)

    @annotation.tailrec
    def countAndSayString(s: String, acc: String): String = {
      if (s.isEmpty) acc
      else {
        val count = s.takeWhile(_ == s.head).length
        countAndSayString(s.substring(count), acc + s"$count${s.head}")
      }
    }

    @annotation.tailrec
    def loop(count: Int, current: String): String = {
      if (count == 0) current
      else loop(count - 1, countAndSayString(current, ""))
    }

    loop(n - 1, "1")
  }
}
