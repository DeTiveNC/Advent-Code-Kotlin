fun main() {
    // Test
    val readTest = readInput("Day04_test")
    check(Day04Class(readTest).solvePart1() == 18)
    check(Day04Class(readTest).solvePart2() == 9)

    // Puzzle
    val readPuzzle = readInput("Day04")
    println("Part 1:" + Day04Class(readPuzzle).solvePart1())
    println("Part 2:" + Day04Class(readPuzzle).solvePart2())
}