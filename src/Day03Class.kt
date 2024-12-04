class Day03Class(private val input: List<String>) {
    fun solvePart1(): Int {
        val regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

        return input.sumOf { linea ->
            regex.findAll(linea)
                .sumOf { result ->
                    val x = result.groupValues[1].toInt()
                    val y = result.groupValues[2].toInt()
                    x * y
                }
        }
    }

    fun solvePart2(): Int {
        var esPosible = true
        val regex = """mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""".toRegex()

        return input.sumOf { linea ->
            var sum = 0
            regex.findAll(linea).forEach { valor ->
                when {
                    valor.value.startsWith("mul") && esPosible -> {
                        sum += (valor.groupValues[1].toInt() * valor.groupValues[2].toInt())
                    }
                    valor.value == "do()" -> esPosible = true // Puede la siguiente funcion realizarse
                    valor.value == "don't()" -> esPosible = false // No puede la siguiente funcion realizarse
                }
            }
            sum
        }
    }
}