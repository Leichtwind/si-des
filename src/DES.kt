import java.util.*

object DES {

  private val IP = listOf(
      58, 50, 42, 34, 26, 18, 10, 2,
      60, 52, 44, 36, 28, 20, 12, 4,
      62, 54, 46, 38, 30, 22, 14, 6,
      64, 56, 48, 40, 32, 24, 16, 8,
      57, 49, 41, 33, 25, 17, 9, 1,
      59, 51, 43, 35, 27, 19, 11, 3,
      61, 53, 45, 37, 29, 21, 13, 5,
      63, 55, 47, 39, 31, 23, 15, 7
  ).map { it - 1 }

  private val E = listOf(
      32, 1, 2, 3, 4, 5,
      4, 5, 6, 7, 8, 9,
      8, 9, 10, 11, 12, 13,
      12, 13, 14, 15, 16, 17,
      16, 17, 18, 19, 20, 21,
      20, 21, 22, 23, 24, 25,
      24, 25, 26, 27, 28, 29,
      28, 29, 30, 31, 32, 1
  ).map { it - 1 }

  private val S = arrayOf(
      listOf(
          14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7,
          0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,
          4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0,
          15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13
      ),

      listOf(
          15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10,
          3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5,
          0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15,
          13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9
      ),

      listOf(
          10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8,
          13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1,
          13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7,
          1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12
      ),

      listOf(
          7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15,
          13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,
          10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,
          3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14
      ),

      listOf(
          2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,
          14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,
          4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,
          11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3
      ),

      listOf(
          12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,
          10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,
          9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,
          4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13
      ),

      listOf(
          4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,
          13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,
          1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,
          6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12
      ),

      listOf(
          13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7,
          1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,
          7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,
          2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11
      )
  )

  private val P = listOf(
      16, 7, 20, 21,
      29, 12, 28, 17,
      1, 15, 23, 26,
      5, 18, 31, 10,
      2, 8, 24, 14,
      32, 27, 3, 9,
      19, 13, 30, 6,
      22, 11, 4, 25
  ).map { it - 1 }

  private val IP2 = listOf(
      40, 8, 48, 16, 56, 24, 64, 32,
      39, 7, 47, 15, 55, 23, 63, 31,
      38, 6, 46, 14, 54, 22, 62, 30,
      37, 5, 45, 13, 53, 21, 61, 29,
      36, 4, 44, 12, 52, 20, 60, 28,
      35, 3, 43, 11, 51, 19, 59, 27,
      34, 2, 42, 10, 50, 18, 58, 26,
      33, 1, 41, 9, 49, 17, 57, 25
  ).map { it - 1 }

  fun encrypt(key: BitSet, message: String): BitSet {
    val subKeys = DESSubKey.computeSubKeys(key)
    val binaryMessage = message.toBitSet()

    return processMessage(binaryMessage, subKeys)
  }

  fun decrypt(key: BitSet, encoded: BitSet): String {
    val subKeys = DESSubKey.computeSubKeys(key).reversedArray()
    val decryptedBinary = processMessage(encoded, subKeys)

    return (0..7).joinToString("") { index ->
      val byte = decryptedBinary[index * 8, (index + 1) * 8]

      (0..7)
          .map { if (byte[it]) '1' else '0' }
          .joinToString("")
          .toInt(2)
          .toChar()
          .toString()
    }
  }

  private fun processMessage(binaryMessage: BitSet, subKeys: Array<BitSet>): BitSet {
    // permute 'message' using table IP
    val permutedMessage = binaryMessage.permute(IP)

    // split 'mp' in half and process the resulting series of 'l' and 'r
    val l = Array(17) { BitSet(32) }
    val r = Array(17) { BitSet(32) }

    l[0] = permutedMessage[0, 32]
    r[0] = permutedMessage[32, 64]

    (1 until 17).forEach {
      l[it] = r[it - 1]

      val fs = f(r[it - 1], subKeys[it - 1])

      l[it - 1].xor(fs)
      r[it] = l[it - 1]
    }

    // amalgamate r[16] and l[16] (in that order) into 'e'
    val e = BitSet(64)

    (0..31).forEach {
      e[it] = r[16][it]
      e[it + 32] = l[16][it]
    }

    // permute 'e' using table IP2
    return e.permute(IP2)
  }

  private fun f(r: BitSet, ks: BitSet): BitSet {
    // permute 'r' using table E
    val er = r.permute(E)

    // xor 'er' with 'ks' and store back into 'er'
    er.xor(ks)

    // process 'er' six bits at a time and store resulting four bits in 'sr'
    val sr = BitSet(32)

    S.forEachIndexed { index, table ->
      val b = (index * 6 until (index + 1) * 6).map { if (er[it]) 1 else 0 }

      val row = 2 * b[0] + b[5]
      val col = 8 * b[1] + 4 * b[2] + 2 * b[3] + b[4]

      val m = table[row * 16 + col]   // apply table S

      m.toString(2).padStart(4, '0').forEachIndexed { charIndex, char ->
        sr[index * 4 + charIndex] = char == '1'
      }
    }

    // permute sr using table P
    return sr.permute(P)
  }
}
