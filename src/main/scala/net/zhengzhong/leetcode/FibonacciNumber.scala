package net.zhengzhong.leetcode

/**
 * 509. Fibonacci Number
 * https://leetcode.com/problems/fibonacci-number/
 */
object FibonacciNumber {

  // Solution #1: Tail recursion.

  def fibUsingTailRecursion(n: Int): Int = {

    @annotation.tailrec
    def loop(counter: Int, prev2: Int, prev1: Int): Int = {
      require(counter <= n)
      if (counter == n) prev2 + prev1
      else loop(counter + 1, prev1, prev2 + prev1)
    }

    if (n == 0) 0
    else if (n == 1) 1
    else loop(2, 0, 1)
  }

  // Solution #2: Recursion with memoization.

  def fibUsingMemo(n: Int): Int = {

    def loop(n: Int, memo: collection.mutable.Map[Int, Int]): Int =
      memo.getOrElseUpdate(n, {
        if (n == 0) 0
        else if (n == 1) 1
        else loop(n - 1, memo) + loop(n - 2, memo)
      })

    loop(n, collection.mutable.Map.empty)
  }

  // Solution #3: A more generic memo solution.

  def memoize[K, V](f: K => V): K => V = {
    val memo = collection.mutable.Map.empty[K, V]
    // Return a function of type `K => V` wrapping the original function `f`.
    (key: K) => memo.getOrElseUpdate(key, f(key))
  }

  val fibWithMemo: Int => Int = memoize { n: Int =>
    if (n == 0) 0
    else if (n == 1) 1
    else fibWithMemo(n - 1) + fibWithMemo(n - 2)
  }

  // Solution #3 bis: Wrap solution #3 into one function.

  def fibWithMemo2(n: Int): Int = {

    def withMutableMemo[K, V](f: K => V): K => V = {
      val memo = collection.mutable.Map.empty[K, V]
      // Return a function of type `K => V` wrapping the original function `f`.
      (key: K) => memo.getOrElseUpdate(key, f(key))
    }

    // NOTE: It's important to declare `doFib` as a `lazy val`, or we will get a compile error:
    // "forward reference to value doFib defined on line N extends over definition of value doFib"
    lazy val doFib: Int => Int = withMutableMemo { n: Int =>
      if (n == 0) 0
      else if (n == 1) 1
      else doFib(n - 1) + doFib(n - 2)
    }

    doFib(n)
  }

  // Solution #4: Immutable memo.

  def fibWithImmutableMemo(n: Int): Int = {

    // Wraps a function `f` with a memoization. The function `f` takes an input of type `K` and
    // an immutable memo of type `Map[K, V]`. It should calculate the value for the input, then
    // return that value with a (potentially) updated memo. The function could recursively call
    // the memoized version of itself.
    def withImmutableMemo[K, V](f: (K, Map[K, V]) => (V, Map[K, V])): (K, Map[K, V]) => (V, Map[K, V]) = {
      (key: K, memo: Map[K, V]) => memo.get(key) match {
        case Some(value) => (value, memo)
        case None =>
          val (value, updatedMemo) = f(key, memo)
          (value, updatedMemo + (key -> value))
      }
    }

    lazy val doFib: (Int, Map[Int, Int]) => (Int, Map[Int, Int]) = withImmutableMemo { (n, memo) =>
      if (n == 0) (0, memo)
      else if (n == 1) (1, memo)
      else {
        // Recursively call the memoized version of itself for `n - 1`.
        val (prev1, memo1) = doFib(n - 1, memo)
        // After `fib(n - 1)` is calculated, we should have cached all Fibonacci numbers from
        // `0` up to `n - 1` in the updated `memo1`. The only exception is when `n == 2`: After
        // calculating `fib(1)`, `0` is not yet cached.
        if (n > 2) {
          assert(memo1 contains (n - 2))
        }
        // The following recursive call should return the value directly from `memo1`.
        val (prev2, memo2) = doFib(n - 2, memo1)
        val result = prev2 + prev1
        (result, memo2)
      }
    }

    val (result, _) = doFib(n, Map.empty)
    result
  }

  // Switch among different implementations.

  val fib: Int => Int = fibWithImmutableMemo

}
