import java.util.*

object DESSubKey {

  private val PC1 = listOf(
      57, 49, 41, 33, 25, 17, 9,
      1, 58, 50, 42, 34, 26, 18,
      10, 2, 59, 51, 43, 35, 27,
      19, 11, 3, 60, 52, 44, 36,
      63, 55, 47, 39, 31, 23, 15,
      7, 62, 54, 46, 38, 30, 22,
      14, 6, 61, 53, 45, 37, 29,
      21, 13, 5, 28, 20, 12, 4
  ).map { it - 1 }

  private val PC2 = listOf(
      14, 17, 11, 24, 1, 5,
      3, 28, 15, 6, 21, 10,
      23, 19, 12, 4, 26, 8,
      16, 7, 27, 20, 13, 2,
      41, 52, 31, 37, 47, 55,
      30, 40, 51, 45, 33, 48,
      44, 49, 39, 56, 34, 53,
      46, 42, 50, 36, 29, 32
  ).map { it - 1 }

  private val SHIFTS = listOf(1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1)

  fun computeSubKeys(key: BitSet): Array<BitSet> {
    // permute 'key' using table PC1
    val permutedKey = key.permute(PC1)

    // split 'kp' in half and process the resulting series of 'c' and 'd'
    val c = Array(16) { BitSet(28) }
    val d = Array(16) { BitSet(28) }

    // compute C1 and D1
    c[0] = permutedKey[0, 28].cshl(SHIFTS[0], 28)
    d[0] = permutedKey[28, 56].cshl(SHIFTS[0], 28)

    // compute C2-C16 and D2-D16
    (1 until 16).forEach { i ->
      c[i] = c[i - 1].cshl(SHIFTS[i], 28)
      d[i] = d[i - 1].cshl(SHIFTS[i], 28)
    }

    // merge c and d to get keys
    val keys = Array(16) {
      BitSet(28).apply {
        (0 until 28).forEach { j ->
          this[j] = c[it][j]
          this[j + 28] = d[it][j]
        }
      }
    }

    // sub keys
    return keys.map { it.permute(PC2) }.toTypedArray()
  }
}