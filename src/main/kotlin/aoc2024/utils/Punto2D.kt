package aoc2024.utils

data class Punto2D(val x: Int, val y: Int) {

    operator fun plus(other: Punto2D): Punto2D =
        Punto2D(x + other.x, y + other.y)

    operator fun minus(other: Punto2D): Punto2D =
        Punto2D(x - other.x, y - other.y)

    fun estaEnGrid(input: List<String>): Boolean =
        y in input.indices && x in input[y].indices

    fun direccionGiro(): Punto2D =
        when (this) {
            NORTE -> ESTE
            ESTE -> SUR
            SUR -> OESTE
            OESTE -> NORTE
            else -> error("Bad direction: $this")
        }

    fun adyacentes() = listOf(
        Punto2D(x - 1, y),
        Punto2D(x + 1, y),
        Punto2D(x, y - 1),
        Punto2D(x, y + 1),
    )

    companion object {
        val NORTE = Punto2D(0, -1)
        val ESTE = Punto2D(1, 0)
        val SUR = Punto2D(0, 1)
        val OESTE = Punto2D(-1, 0)
    }
}