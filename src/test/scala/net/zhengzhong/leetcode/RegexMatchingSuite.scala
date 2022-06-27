package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.RegexMatching.isMatch
import org.junit.Assert.{assertFalse, assertTrue}
import org.junit.Test

class RegexMatchingSuite {

  @Test
  def test_is_match(): Unit = {
    assertTrue(isMatch("", "a*"))
    assertFalse(isMatch("aa", "a"))
    assertTrue(isMatch("aa", "a*"))
    assertTrue(isMatch("ab", ".*"))
    assertTrue(isMatch("aab", "c*a*b"))
    assertTrue(isMatch("aaaaaaaaab", "a*b*c*d*"))
  }
}
