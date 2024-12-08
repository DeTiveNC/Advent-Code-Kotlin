package aoc2024

data class Punto2D(val x: Int, val y: Int) {

    operator fun plus(other: Punto2D): Punto2D =
        Punto2D(x + other.x, y + other.y)

    operator fun minus(other: Punto2D): Punto2D =
        Punto2D(x - other.x, y - other.y)

    companion object {
        val NORTE = Punto2D(0, -1)
        val ESTE = Punto2D(1, 0)
        val SUR = Punto2D(0, 1)
        val OESTE = Punto2D(-1, 0)
    }
}
