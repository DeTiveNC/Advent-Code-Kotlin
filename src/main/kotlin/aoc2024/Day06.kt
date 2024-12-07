package aoc2024

class Day06(private val input: List<String>) {
    private val grid: List<CharArray> = input.map { it.toCharArray() }

    private val start: Punto2D = grid
        .flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == '^') Punto2D(x, y) else null
            }
        }.filterNotNull().first()

    fun parte1(): Int = moverse().first.size

    fun parte2(): Int =
        moverse()
            .first
            .filterNot { it == start }
            .count { candidate ->
                grid[candidate] = '#'
                moverse().also { grid[candidate] = '.' }.second
            }

    private fun moverse(): Pair<Set<Punto2D>, Boolean> {
        val seen = mutableSetOf<Pair<Punto2D, Punto2D>>()
        var location = start
        var direction = Punto2D.NORTE

        while (grid[location] != null && (location to direction) !in seen) {
            seen += location to direction
            val next = location + direction

            if (grid[next] == '#') direction = direction.direccionGiro()
            else location = next
        }
        return seen.map { it.first }.toSet() to (grid[location] != null)
    }

    private operator fun List<CharArray>.get(at: Punto2D): Char? =
        getOrNull(at.y)?.getOrNull(at.x)

    private operator fun List<CharArray>.set(at: Punto2D, c: Char) {
        this[at.y][at.x] = c
    }

    private fun Punto2D.direccionGiro(): Punto2D =
        when (this) {
            Punto2D.NORTE -> Punto2D.ESTE
            Punto2D.ESTE -> Punto2D.SUR
            Punto2D.SUR -> Punto2D.OESTE
            Punto2D.OESTE -> Punto2D.NORTE
            else -> error("Bad direction: $this")
        }


    data class Punto2D(val x: Int, val y: Int) {

        operator fun plus(other: Punto2D): Punto2D =
            Punto2D(x + other.x, y + other.y)

        companion object {
            val NORTE = Punto2D(0, -1)
            val ESTE = Punto2D(1, 0)
            val SUR = Punto2D(0, 1)
            val OESTE = Punto2D(-1, 0)
        }
    }
}
