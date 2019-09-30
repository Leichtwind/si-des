import java.util.*

/**
 * Cyclically shifts this value left by the bitCount number of bits.
 */
fun BitSet.cshl(bitCount: Int, length: Int): BitSet {
  return this[0, length].apply {
    // perform shifts
    (1..bitCount).forEach { _ ->
      val firstBit = this[0]

      (1 until length).forEach {
        this[it - 1] = this[it]
      }

      this[length - 1] = firstBit
    }
  }
}

fun BitSet.permute(permutationTable: List<Int>) = BitSet(permutationTable.size).apply {
  permutationTable.forEachIndexed { index, position -> this@apply[index] = this@permute[position] }
}

fun BitSet.toBinaryString(): String {
  return (0 until this.length()).joinToString("") { if (this[it]) "1" else "0" }
}
