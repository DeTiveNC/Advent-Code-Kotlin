import kotlin.math.abs

fun main() {
    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(Day01Class(testInput).distance() == 11)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    println("Part1: " + Day01Class(input).distance())
    println("Part2: " + Day01Class(input).timesValue())
}
