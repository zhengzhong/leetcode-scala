package net.zhengzhong.leetcode

/**
 * 745. Prefix and Suffix Search
 * https://leetcode.com/problems/prefix-and-suffix-search/
 */
class WordFilter(_words: Array[String]) {

  // Solution #1: Build a prefix trie and a suffix trie (Time Limit Exceeded) ---------------------

  class TrieNode(val char: Char) {

    private val children: collection.mutable.Map[Char, TrieNode] = collection.mutable.Map.empty
    private var index: Int = -1

    private def locate(chars: List[Char]): Option[TrieNode] = chars match {
      case Nil => Some(this)
      case head :: tail => children.get(head).flatMap(_.locate(tail))
    }

    @annotation.tailrec
    private def grow(chars: List[Char]): TrieNode = chars match {
      case Nil => this
      case head :: tail => children.getOrElseUpdate(head, new TrieNode(head)).grow(tail)
    }

    def addWordIndex(word: String, index: Int): Unit = {
      val node = grow(word.toList)
      node.index = node.index max index
    }

    /**
     * Return all the indices recorded in the subtree rooted from this node.
     */
    private def allIndices: Set[Int] = {
      val myIndexSet = if (index == -1) Set.empty[Int] else Set(index)
      children.values.toSeq.map(_.allIndices).fold(myIndexSet)(_ | _)
    }

    def lookupIndices(prefix: String): Set[Int] = locate(prefix.toList) match {
      case None => Set.empty
      case Some(node) => node.allIndices
    }
  }

  class WordFilterWithTwoTries(words: Array[String]) {
    val prefixTrie = new TrieNode('^')
    val suffixTrie = new TrieNode('$')

    words.zipWithIndex.foreach {
      case (word, index) =>
        prefixTrie.addWordIndex(word, index)
        suffixTrie.addWordIndex(word.reverse, index)
    }

    def lookupMaxIndex(prefix: String, suffix: String): Int = {
      // NOTE: This solution will not pass the tests ("Time Limit Exceeded"), because the indices
      // by prefix and suffix could be very big sets.
      val indicesByPrefix = prefixTrie.lookupIndices(prefix)
      val indicesBySuffix = suffixTrie.lookupIndices(suffix.reverse)
      val indices = indicesByPrefix & indicesBySuffix
      if (indices.isEmpty) -1 else indices.max
    }
  }

  // Solution #2: Build a paired trie (Accepted ) -------------------------------------------------

  type CharPair = (Char, Char)

  class PairedTrieNode(_key: CharPair) {

    val key: CharPair = _key
    val children: collection.mutable.Map[CharPair, PairedTrieNode] = collection.mutable.Map.empty
    var maxIndex: Int = -1

    @annotation.tailrec
    private def locate(path: List[CharPair]): Option[PairedTrieNode] = path match {
      case Nil => Some(this)
      case head :: tail => children.get(head) match {
        case None => None
        case Some(node) => node.locate(tail)
      }
    }

    /**
     * Propagate the word index down the trie. Every node will remember the max index of the word
     * that matches the prefix and suffix.
     */
    private def propagate(wordPath: List[CharPair], index: Int): Unit = {
      maxIndex = maxIndex max index
      wordPath match {
        case Nil => ()
        case (prefix, suffix) :: tail =>
          // In a full path, there should be no wildcard `_`.
          assert(prefix != '_' && suffix != '_')
          val childKeys = key match {
            case ('_', c) if c != '_' =>
              List(('_', suffix))
            case (c, '_') if c != '_' =>
              List((prefix, '_'))
            case _ =>
              List((prefix, suffix), ('_', suffix), (prefix, '_'))
          }
          for (childKey <- childKeys) {
            val child = children.getOrElseUpdate(childKey, new PairedTrieNode(childKey))
            child.propagate(tail, index)
          }
      }
    }

    def addWordIndex(word: String, index: Int): Unit = {
      val wordPath = (word zip word.reverse).toList
      propagate(wordPath, index)
    }

    def lookupMaxIndex(prefix: String, suffix: String): Int = {
      val path = prefix.zipAll(suffix.reverse, '_', '_').toList
      locate(path) match {
        case None => -1
        case Some(node) => node.maxIndex
      }
    }
  }

  class WordFilterWithPairedTrie(words: Array[String]) {
    val pairedTrie = new PairedTrieNode(('^', '$'))
    for ((word, index) <- words.zipWithIndex) {
      pairedTrie.addWordIndex(word, index)
    }

    def lookupMaxIndex(prefix: String, suffix: String): Int = {
      pairedTrie.lookupMaxIndex(prefix, suffix)
    }
  }

  // ----------------------------------------------------------------------------------------------

  // val impl = new WordFilterWithTwoTries(_words)
  val impl = new WordFilterWithPairedTrie(_words)

  def f(prefix: String, suffix: String): Int = impl.lookupMaxIndex(prefix, suffix)

}
