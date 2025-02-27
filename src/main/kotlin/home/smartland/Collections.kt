package home.smartland

object Collections {
    @JvmStatic
    fun main(args: Array<String>) {
        val list: List<Int> = listOf(1, 2, 3, 4, 5)
        println("list is $list")

        // FP APi
        val res = list.flatMap { i ->
            println("elem is ${i}")
            listOf(i * 2);
        }
        println("res is $res")
        list + 1
        println("now tail list is ${list.drop(1)}")
        val set = mutableSetOf(null).add(null)
        set.let { println("set is $it") }

        val mutableNumbers = mutableListOf<Long>()
        mutableNumbers.also { println("init mutableNumbers is $it") }
            .apply {
                add(-42)
                addFirst(0)
            }
            .apply { println("added mutableNumbers is $this") }
            .sort()
            .run { println("sorted mutableNumbers is $this") }

        with(mutableNumbers) {
            val firstItem = first()
            val lastItem = last()
            val tail = drop(1)
            add(-3)
            println("First was $firstItem added mutableNumbers is $this")
            sort()
        }

        println("finally sorted mutableNumbers is ${mutableNumbers}")
        mutableNumbers
            .let(::print)
    }
}