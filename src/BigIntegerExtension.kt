import java.math.BigInteger
import java.util.*

fun BigInteger.toBitSet(): BitSet {
  val binaryString = this.toString(2).reversed()

  return BitSet(binaryString.length).apply {
    binaryString.forEachIndexed { index, char ->
      this[index] = char == '1'
    }
  }
}
