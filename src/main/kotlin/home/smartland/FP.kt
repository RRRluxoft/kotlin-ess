package home.smartland

object FP {

    val tenXfn: (Int) -> Int = fun (x: Int) = x * 10
    val tenXfn_V2: (Int) -> Int = { x -> x * 10 }

    val adderFun = { x: Int, y: Int -> x + y }

    val numbers = listOf(5, 2, 3, 8)
    val sum = numbers.reduce { sum, element -> sum + element }
    val texX = numbers.map { tenXfn(it) }
    val texX_V2 = numbers.map { x -> x * 10 } // type inference
    val texX_V3 = numbers.map { it * 10 }

    val evenNumbers = numbers.filter { it % 2 == 0 }
}