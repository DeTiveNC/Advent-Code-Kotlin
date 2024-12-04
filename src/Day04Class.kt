class Day04Class(private val input : List<String>) {
    fun solvePart1(): Int =
        input.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == 'X') {
                    ALL_DIRECTIONS.count { vector ->
                        vectorFind("XMAS", x, y, vector)
                    }
                } else 0
            }
        }.sum()

    fun solvePart2(): Int =
        input.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == 'A') {
                    CORNERS
                        .map { (dx, dy) -> input.safeAt(x + dx, y + dy) }
                        .joinToString("") in setOf("MMSS", "MSSM", "SSMM", "SMMS")
                } else false
            }
        }.count { it }

    private fun List<String>.safeAt(x: Int, y: Int): Char =
        if (y in indices && x in this[y].indices) this[y][x] else ' '

    private tailrec fun vectorFind(target: String, x: Int, y: Int, vector: Pair<Int, Int>): Boolean =
        when {
            target.isEmpty() -> true
            target.first() != input.safeAt(x, y) -> false
            else -> vectorFind(target.substring(1), x + vector.first, y + vector.second, vector)
        }

    private companion object {
        val ALL_DIRECTIONS = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1, 0 to 1,
            1 to -1, 1 to 0, 1 to 1
        )
        val CORNERS = listOf(-1 to -1, -1 to 1, 1 to 1, 1 to -1)
    }

}