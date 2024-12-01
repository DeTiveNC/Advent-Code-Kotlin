import kotlin.math.abs

fun main() {
    fun distance(input: List<String>): Int {
        val ( listOne, listTwo ) = devolverDosListas(input)

        val listSortedOne = listOne.sorted()
        val listSortedTwo = listTwo.sorted()

        val result = listSortedOne.zip(listSortedTwo).sumOf { (left, right) ->
            abs(left - right)
        }

        return result
    }

    fun timesValue(input: List<String>): Int {
        val ( listOne, listTwo) = devolverDosListas(input)

        val frecuencyOfValueSecondList = listTwo.groupingBy { it }.eachCount()

        val result = listOne.sumOf { value ->
            value * (frecuencyOfValueSecondList[value] ?: 0)
        }

        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(distance(testInput) == 11)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    println("Part1: " + distance(input))
    println("Part2: " + timesValue(input))
}
