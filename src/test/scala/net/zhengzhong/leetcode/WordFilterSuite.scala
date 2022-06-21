package net.zhengzhong.leetcode

import org.junit.Assert.assertEquals
import org.junit.Test

class WordFilterSuite {

  @Test
  def test_word_filter(): Unit = {
    val wordFilter = new WordFilter(Array("apple", "banana", "orge", "orange"))
    assertEquals(0, wordFilter.f("a", "e"))
    assertEquals(0, wordFilter.f("ap", "le"))
    assertEquals(3, wordFilter.f("o", "e"))
    assertEquals(3, wordFilter.f("or", "ge"))
    assertEquals(-1, wordFilter.f("c", "e"))
  }
}
