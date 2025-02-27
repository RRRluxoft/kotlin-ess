package home.smartland

object OOP {

    open class Pet {
        open fun makeNoise() = "noise"
        companion object {
            const val SPECIES = "Pet"
        }
    }

    class Cat(name: String) : Pet() {
        override fun makeNoise() = "meow"
    }

    class Dog(name: String) : Pet() {
        //        override fun makeNoise() = "bark"
        companion object {
            const val SPECIES = "Canine"
            fun of(name: String) = Dog(name)
        }
    }

    // hashCode , equals, toString
    // copy
    // destructuring
    data class Person(val name: String, val age: Int) {

    }


    @JvmStatic
    fun main(args: Array<String>) {
        val tom = Person("Tom", 30)
        val (name, age) = tom
        println("name is $name and age is $age")
    }
}