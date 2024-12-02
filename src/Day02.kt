fun main(){
    fun obtenerSafeCount(input : List<String>, part: Int): Int {
        return devolverInt(input, part)
    }

    val inputTest = readInput("Day02_test")
    check(obtenerSafeCount(inputTest, 1) == 2)
    check(obtenerSafeCount(inputTest, 2) == 4)

    val input = readInput("Day02")
    println("Part 1:" + obtenerSafeCount(input,1))
    println("Part 2:" + obtenerSafeCount(input,2))
}