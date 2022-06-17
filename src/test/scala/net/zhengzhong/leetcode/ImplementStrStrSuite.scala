package net.zhengzhong.leetcode

import org.junit.Assert.assertEquals
import org.junit.Test

class ImplementStrStrSuite {

  import ImplementStrStr._

  @Test
  def test_str_str(): Unit = {
    assertEquals(strStr("hello", "ll"), 2)
    assertEquals(strStr("aaaaa", "bba"), -1)
    assertEquals(strStr("aaaaa", "aaa"), 0)
    assertEquals(strStr("aaaaa", "aaaaa"), 0)
    assertEquals(strStr("aaaaa", "aaaaaa"), -1)
  }
}
