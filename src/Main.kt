import java.math.BigInteger

fun main() {
  val key = BigInteger("10374954171461414088")
  val message = "Hi, Mark"

  val bs = key.toBitSet()

  val encrypted = DES.encrypt(bs, message)
  val decrypted = DES.decrypt(bs, encrypted)

  println("""
    Original message: $message
    Encrypted: ${encrypted.toBinaryString()}
    Decrypted: $decrypted
  """.trimIndent())

//  Print SubKeys
//  subKeys.forEach { subKey ->
//    println(
//        (0..47).map { if (subKey[it]) '1' else '0' }.joinToString("")
//    )
//  }
}
