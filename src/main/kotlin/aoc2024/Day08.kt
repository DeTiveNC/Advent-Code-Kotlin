package aoc2024

import aoc2024.utils.Punto2D

class Day08(private val input: List<String>) {

    private val nodos: Collection<List<Punto2D>> = parseGrid(input)

    fun parte1(): Int =
        contarAntiNodos(::antiNodosParaPart1)

    fun parte2(): Int =
        contarAntiNodos(::antiNodosParaPart2)

    private fun contarAntiNodos(worker: (Punto2D, Punto2D, Punto2D) -> Set<Punto2D>): Int =
        nodos.flatMap { listaNodo ->
            listaNodo.flatMapIndexed { i, a ->
                listaNodo.drop(i + 1).flatMap { b ->
                    worker.invoke(a, b, a - b)
                }
            }.filter { it.estaEnGrid(input) }
        }.toSet().size

    private fun antiNodosParaPart1(a: Punto2D, b: Punto2D, diff: Punto2D): Set<Punto2D> =
        if (a.y > b.y) setOf(a - diff, b + diff)
        else setOf(a + diff, b - diff)

    private fun antiNodosParaPart2(a: Punto2D, b: Punto2D, diff: Punto2D): Set<Punto2D> =
        generateSequence(a) { it - diff }.takeWhile { it.estaEnGrid(input) }.toSet() +
                generateSequence(b) { it + diff }.takeWhile { it.estaEnGrid(input) }.toSet()


    private fun parseGrid(input: List<String>): Collection<List<Punto2D>> =
        input.flatMapIndexed { y, filas ->
            filas.mapIndexed { x, c ->
                if (c != '.') c to Punto2D(x, y) else null
            }
        }.filterNotNull()
            .groupBy({ it.first }, { it.second })
            .values


}