import java.util.*

fun String.toBitSet(): BitSet {
  val binaryString = this
      .map { it.toInt().toString(2).padStart(8, '0') }
      .joinToString("")

  return BitSet(binaryString.length).apply {
    binaryString.forEachIndexed { index, char ->
      this[index] = char == '1'
    }
  }
}
