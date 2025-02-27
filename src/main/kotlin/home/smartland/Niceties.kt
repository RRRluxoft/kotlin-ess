package home.smartland

import org.intellij.lang.annotations.Language

object Niceties {

    data class Developer(val name: String, val favLanguage: String) {
        infix fun likes(another: String) =
            "$name developer  likes $another also"
    }

    val maybeDev: Developer? = null

    // extension:
    fun Int.repeat(str: String): String = // str.repeat()
        (1..this)
            .fold("") { acc, _ -> acc + str }

    @JvmStatic
    fun main(args: Array<String>) {
        val maybeDev: Developer? = null
        val tomcat: Developer = Developer(name = "Tomcat", favLanguage = "Scala")
        val noName = maybeDev?.name ?: "John"
        val statement = tomcat likes "Kotlin"
        statement.let(::println)

        "Bom-".repeat(5).let(::println)
        5.repeat("Bom-").let(::println)
    }

}