fun main() {

    fun part1(input: List<String>): Int {
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

    fun part2(input: List<String>): Int {
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

    // Test
    val readTest = readInput("Day03_test")
    val readTest2 = readInput("Day03_test_2")
    check(part1(readTest) == 161)
    check(part2(readTest2) == 48)

    // Result
    val readPuzzle = readInput("Day03")
    val readPuzzle2 = readInput("Day03_2")
    println("Part 1: " + part1(readPuzzle))
    println("Part 2: " + part2(readPuzzle2))
}