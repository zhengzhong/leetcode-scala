package net.zhengzhong.leetcode

/**
 * 10. Regular Expression Matching
 * https://leetcode.com/problems/regular-expression-matching/
 */
object RegexMatching {

  sealed trait RegexToken {

    /**
     * Length of this token in the pattern string.
     */
    def length: Int

    /**
     * Try to match the string `s` starting from `index`. Return a pair of next min / max index
     * after the match, or `None` if the token does not match the string.
     */
    def matchFrom(s: String, index: Int): Option[(Int, Int)]
  }

  case class SingleChar(char: Char) extends RegexToken {

    val length: Int = 1

    override def matchFrom(s: String, index: Int): Option[(Int, Int)] = {
      if (index == s.length) None
      else if (char == '.' || char == s(index)) Some((index + 1, index + 1))
      else None
    }
  }

  case class ZeroOrMore(char: Char) extends RegexToken {

    val length: Int = 2

    override def matchFrom(s: String, index: Int): Option[(Int, Int)] = {
      // NOTE: A "zero or more" token can always match nothing, so its min next index is `index` itself.
      val minNextIndex = index
      val maxNextIndex = {
        if (index == s.length || char == '.') s.length
        else {
          val endIndex = s.indexWhere(_ != char, index)
          if (endIndex == -1) s.length else endIndex
        }
      }
      Some((minNextIndex, maxNextIndex))
    }
  }

  def parsePattern(pattern: String): List[RegexToken] = {

    @annotation.tailrec
    def parse(index: Int, acc: List[RegexToken]): List[RegexToken] = {
      if (index == pattern.length) acc
      else {
        val char = pattern(index)
        val token =
          if (index + 1 < pattern.length && pattern(index + 1) == '*') ZeroOrMore(char)
          else SingleChar(char)
        parse(index + token.length, token :: acc)
      }
    }

    parse(0, Nil).reverse
  }

  def regexMatch(s: String, begin: Int, tokens: List[RegexToken]): Boolean = tokens match {
    case Nil => begin == s.length
    case token :: moreTokens =>
      token.matchFrom(s, begin) match {
        case None => false
        case Some((minNextBegin, maxNextBegin)) =>
          assert(begin <= minNextBegin && minNextBegin <= maxNextBegin && maxNextBegin <= s.length)
          (minNextBegin to maxNextBegin).exists(regexMatch(s, _, moreTokens))
      }
  }

  def isMatch(s: String, p: String): Boolean = {
    regexMatch(s, 0, parsePattern(p))
  }
}
