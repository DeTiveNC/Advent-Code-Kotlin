fun main() {
    // Test
    val readTest = readInput("Day03_test")
    val readTest2 = readInput("Day03_test_2")
    check(Day03Class(readTest).solvePart1() == 161)
    check(Day03Class(readTest2).solvePart2() == 48)

    // Result
    val readPuzzle = readInput("Day03")
    val readPuzzle2 = readInput("Day03_2")
    println("Part 1: " + Day03Class(readPuzzle).solvePart1())
    println("Part 2: " + Day03Class(readPuzzle2).solvePart2())
}