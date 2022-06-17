package net.zhengzhong.leetcode

import org.junit.Assert.assertEquals
import org.junit.Test

class LetterCombinationsSuite {
  import LetterCombinations._

  @Test
  def test_letter_combinations(): Unit = {
    assertEquals(letterCombinations(""), List())
    assertEquals(letterCombinations("2").sorted, List("a", "b", "c").sorted)
    assertEquals(
      letterCombinations("23").sorted,
      List("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf").sorted
    )
  }

}
