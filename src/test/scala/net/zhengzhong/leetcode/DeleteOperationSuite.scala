package net.zhengzhong.leetcode

import net.zhengzhong.leetcode.DeleteOperation.minDistance
import org.junit.Assert.assertEquals
import org.junit.Test

class DeleteOperationSuite {

  @Test
  def test_min_distance(): Unit = {
    assertEquals(2, minDistance("sea", "eat"))
    assertEquals(4, minDistance("leetcode", "etco"))
    assertEquals(6, minDistance("leetcode", "eletcedo"))
  }
}
