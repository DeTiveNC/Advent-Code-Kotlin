package aoc2024

import aoc2024.utils.Punto2D

class Day10(input: List<String>) {
    private val grid = input.flatMapIndexed { y, line ->
        line.mapIndexed { x, char ->
            Punto2D(x, y) to char.digitToInt()
        }
    }.toMap().withDefault { -1 }

    private val trailheads = grid.entries.filter { it.value == 0 }.map { it.key }

    fun resolverParte1() = trailheads.sumOf { it.score(onlyDistinct = true) }

    fun resolverParte2() = trailheads.sumOf { it.score(onlyDistinct = false) }

    private fun Punto2D.score(onlyDistinct: Boolean, visited: MutableSet<Punto2D> = mutableSetOf()): Int {
        if (onlyDistinct) {
            if (this in visited) return 0
            visited.add(this)
        }

        if (grid[this] == 9) return 1

        return adyacentes()
            .filter { grid.getValue(it) - grid.getValue(this) == 1 }
            .sumOf { it.score(onlyDistinct, visited) }
    }
}