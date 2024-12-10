package aoc2024

class Day05(input: List<String>) {
    private val reglas = input.takeWhile { it != "" }.fold(mapOf<Int, Set<Int>>()) { map, line ->
        val (f, s) = line.split("|")
        map + (f.toInt() to map.getOrElse(f.toInt()) { emptySet() } + s.toInt())
    }
    private val actualizados = input
        .dropWhile { it != "" }
        .drop(1)
        .map { it.split(",").map { n -> n.toInt() } }

    fun resolverParte1() = actualizados.filter { it.esValido(reglas) }.sumOf { it[it.size / 2] }

    fun resolverParte2() = actualizados
        .filter { !it.esValido(reglas) }
        .map { it.reOrdenar(reglas) }
        .sumOf { it[it.size / 2] }

    private fun List<Int>.reOrdenar(reglas: Map<Int, Set<Int>>): List<Int> =
        sortedByDescending { reglas.getOrDefault(it, emptySet()).intersect(this.toSet()).size }

    private fun List<Int>.esValido(reglas: Map<Int, Set<Int>>): Boolean =
        indices
            .all { index ->
                val after = this.subList(index + 1, this.size)
                val number = this[index]
                !after.any { a -> reglas.getOrDefault(a, emptySet()).contains(number)
                }
            }
}