package net.zhengzhong.leetcode

/**
 * 204. Count Primes
 * https://leetcode.com/problems/count-primes/
 */
object CountPrimes {
  def countPrimes(n: Int): Int = {
    if (n <= 1) 0
    else {
      // Sieve of Eratosthenes: <https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes>
      // To make sure that we do not exceed the memory limit, we use an `Array[Boolean]`
      // which holds only Boolean values and is mutable.
      val primeFlags: Array[Boolean] = Array.fill(n)(true)
      primeFlags(0) = false
      primeFlags(1) = false

      @annotation.tailrec
      def count(index: Int, acc: Int): Int = {
        if (index >= n) acc
        else if (!primeFlags(index)) count(index + 1, acc)
        else {
          for (i <- index + index until n by index) {
            primeFlags(i) = false
          }
          count(index + 1, acc + 1)
        }
      }

      count(2, 0)
    }
  }

  // NOTE: This implementation uses too much memory. It is also not good in speed, because it is
  // basically a trial division algorithm.
  def countPrimes_v2(n: Int): Int = {
    def numbersFrom(x: Int): LazyList[Int] = {
      if (x >= n) LazyList()
      else x #:: numbersFrom(x + 1)
    }

    @annotation.tailrec
    def count(nums: LazyList[Int], acc: Int): Int = {
      if (nums.isEmpty) acc
      else if (nums.head * nums.head >= n) acc + nums.size
      else count(nums.tail.filter(_ % nums.head != 0), acc + 1)
    }

    count(numbersFrom(2), 0)
  }
}
