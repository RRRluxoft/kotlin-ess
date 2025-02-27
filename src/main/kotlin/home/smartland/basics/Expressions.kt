package home.smartland.basics

import java.math.BigInteger
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

fun main() {
    val mol: Int = 40 + 2

    // bitwise operators: or, xor, inv, shl, shr
    val shiftLeft = mol shl 2
    println("mol is $mol \nand shiftLeft by 2 is $shiftLeft")

    val expr = mol === 40 + 2

    // switch on steroid
    when (mol) {
        42, 42 -> println("expr is true")
        else -> println("expr is false")
    }

    val molExpr = when (mol) {
        42, 43 -> "expr is true"
//        (mol < 100) -> "mol is less than 100"
        else -> "expr is false"
    }

    // types
    val smth: Any = 42
    val someExpr = when (smth) {
        is Double -> smth + 1.1
        is Int -> smth + 1
        is String -> smth.length
        else -> 0
    }
    println("someExpr is $someExpr")

    // looping:
    for (i in 1..10 step 2) {
        println("i is $i")
    }

    fun combineStrings(vararg strings: String) = strings.joinToString()
    fun combineStrings_V1(strA: String, strB: String) = "$strA---$strB"

    // recursion
    fun concatenateStringRec(str: String, count: Int, acc: String = ""): String =
        if (count <= 0) acc
        else concatenateStringRec(str, count - 1, acc + str)

    // tailrec -
    fun factorial(n: Long, acc: BigInteger = BigInteger.ONE): BigInteger =
        if (n <= 1) acc
        else factorial(n - 1, acc * BigInteger.valueOf(n))
    println("5000! is ${factorial(5000)}")

    val optional: Optional<Int> = Optional.of(42)
    val value: Int? = optional.getOrNull()
    println("Optional value is $value")




}
