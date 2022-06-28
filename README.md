# LeetCode using Scala

Solve [LeetCode](https://leetcode.com/) problems in Scala, to get myself familiar with the Scala
language and to learn how to think in Scala.

To build the project and run unit tests:

    $ ./gradlew clean build

## Problems and solutions

<!--- INDEX:BEGIN -->

| Problem                                                                                              | Source Code                                                                                          |
| ---------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------- |
| [1. Two Sum](https://leetcode.com/problems/two-sum/)                                                 | [`TwoSum.scala`](src/main/scala/net/zhengzhong/leetcode/TwoSum.scala)                                |
| [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)                                 | [`AddTwoNumbers.scala`](src/main/scala/net/zhengzhong/leetcode/AddTwoNumbers.scala)                  |
| [4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)         | [`MedianOfTwoSortedArrays.scala`](src/main/scala/net/zhengzhong/leetcode/MedianOfTwoSortedArrays.scala) |
| [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)     | [`LongestPalindromicSubstring.scala`](src/main/scala/net/zhengzhong/leetcode/LongestPalindromicSubstring.scala) |
| [9. Palindrome Number](https://leetcode.com/problems/palindrome-number/)                             | [`PalindromeNumber.scala`](src/main/scala/net/zhengzhong/leetcode/PalindromeNumber.scala)            |
| [10. Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/)        | [`RegexMatching.scala`](src/main/scala/net/zhengzhong/leetcode/RegexMatching.scala)                  |
| [12. Integer to Roman](https://leetcode.com/problems/integer-to-roman/)                              | [`IntegerToRoman.scala`](src/main/scala/net/zhengzhong/leetcode/IntegerToRoman.scala)                |
| [13. Roman to Integer](https://leetcode.com/problems/roman-to-integer/)                              | [`RomanToInteger.scala`](src/main/scala/net/zhengzhong/leetcode/RomanToInteger.scala)                |
| [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/)                    | [`LongestCommonPrefix.scala`](src/main/scala/net/zhengzhong/leetcode/LongestCommonPrefix.scala)      |
| [17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) | [`LetterCombinations.scala`](src/main/scala/net/zhengzhong/leetcode/LetterCombinations.scala) |
| [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)                  | [`MergeTwoSortedLists.scala`](src/main/scala/net/zhengzhong/leetcode/MergeTwoSortedLists.scala)      |
| [28. Implement strStr()](https://leetcode.com/problems/implement-strstr/)                            | [`ImplementStrStr.scala`](src/main/scala/net/zhengzhong/leetcode/ImplementStrStr.scala)              |
| [38. Count and Say](https://leetcode.com/problems/count-and-say/)                                    | [`CountAndSay.scala`](src/main/scala/net/zhengzhong/leetcode/CountAndSay.scala)                      |
| [51. N-Queens](https://leetcode.com/problems/n-queens/)                                              | [`NQueens.scala`](src/main/scala/net/zhengzhong/leetcode/NQueens.scala)                              |
| [51. N-Queens II](https://leetcode.com/problems/n-queens-ii/)                                        | [`NQueens2.scala`](src/main/scala/net/zhengzhong/leetcode/NQueens2.scala)                            |
| [61. Rotate List](https://leetcode.com/problems/rotate-list/)                                        | [`RotateList.scala`](src/main/scala/net/zhengzhong/leetcode/RotateList.scala)                        |
| [73. Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/)                            | [`SetMatrixZeros.scala`](src/main/scala/net/zhengzhong/leetcode/SetMatrixZeros.scala)                |
| [120. Triangle](https://leetcode.com/problems/triangle/)                                             | [`Triangle.scala`](src/main/scala/net/zhengzhong/leetcode/Triangle.scala)                            |
| [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) | [`TwoSum2.scala`](src/main/scala/net/zhengzhong/leetcode/TwoSum2.scala)                        |
| [189. Rotate Array](https://leetcode.com/problems/rotate-array/)                                     | [`RotateArray.scala`](src/main/scala/net/zhengzhong/leetcode/RotateArray.scala)                      |
| [204. Count Primes](https://leetcode.com/problems/count-primes/)                                     | [`CountPrimes.scala`](src/main/scala/net/zhengzhong/leetcode/CountPrimes.scala)                      |
| [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | [`KthLargestElement.scala`](src/main/scala/net/zhengzhong/leetcode/KthLargestElement.scala)        |
| [297. Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) | [`BinaryTreeCodec.scala`](src/main/scala/net/zhengzhong/leetcode/BinaryTreeCodec.scala) |
| [304. Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/)   | [`NumMatrix.scala`](src/main/scala/net/zhengzhong/leetcode/NumMatrix.scala)                          |
| [509. Fibonacci Number](https://leetcode.com/problems/fibonacci-number/)                             | [`FibonacciNumber.scala`](src/main/scala/net/zhengzhong/leetcode/FibonacciNumber.scala)              |
| [583. Delete Operation for Two Strings](https://leetcode.com/problems/delete-operation-for-two-strings/) | [`DeleteOperation.scala`](src/main/scala/net/zhengzhong/leetcode/DeleteOperation.scala)          |
| [745. Prefix and Suffix Search](https://leetcode.com/problems/prefix-and-suffix-search/)             | [`WordFilter.scala`](src/main/scala/net/zhengzhong/leetcode/WordFilter.scala)                        |
| [867. Transpose Matrix](https://leetcode.com/problems/transpose-matrix/)                             | [`TransposeMatrix.scala`](src/main/scala/net/zhengzhong/leetcode/TransposeMatrix.scala)              |
| [968. Binary Tree Cameras](https://leetcode.com/problems/binary-tree-cameras/)                       | [`BinaryTreeCameras.scala`](src/main/scala/net/zhengzhong/leetcode/BinaryTreeCameras.scala)          |
| [1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)        | [`LongestCommonSubsequence.scala`](src/main/scala/net/zhengzhong/leetcode/LongestCommonSubsequence.scala) |
| [1642. Furthest Building You Can Reach](https://leetcode.com/problems/furthest-building-you-can-reach/) | [`FurthestBuilding.scala`](src/main/scala/net/zhengzhong/leetcode/FurthestBuilding.scala)         |
| [1689. Partitioning Into Minimum Number Of Deci-Binary Numbers](https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/) | [`MinPartitions.scala`](src/main/scala/net/zhengzhong/leetcode/MinPartitions.scala) |

<!--- INDEX:END -->
