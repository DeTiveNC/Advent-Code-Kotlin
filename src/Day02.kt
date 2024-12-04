fun main(){
    val inputTest = readInput("Day02_test")
    check(Day02Class(inputTest).obtenerSafeCount(1) == 2)
    check(Day02Class(inputTest).obtenerSafeCount(2) == 4)

    val input = readInput("Day02")
    println("Part 1:" + Day02Class(input).obtenerSafeCount(1))
    println("Part 2:" + Day02Class(input).obtenerSafeCount(2))
}