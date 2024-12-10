package aoc2024

class Day07(input: List<String>) {

    private val ecuaciones: List<List<Long>> = input
        .map { it.split("""\D+""".toRegex()).map { it.toLong() } }

    private val operadores: List<(Long, Long) -> Long> = listOf(
        { a, b -> a + b },
        { a, b -> a * b }
    )

    fun parte1(): Long = resolverOperacion(operadores)

    fun parte2(): Long = resolverOperacion(operadores + { a, b -> "$a$b".toLong() })

    private fun resolverOperacion(operacionesValidas: List<(Long, Long) -> Long>): Long =
        ecuaciones
            .filter { tieneSolucion(operacionesValidas, it[0], it[1], it.subList(2, it.size)) }
            .sumOf { it.first() }

    private fun tieneSolucion(
        operadores: List<(Long, Long) -> Long>,
        valTotal: Long,
        valorPri: Long,
        restValores: List<Long>
    ): Boolean =
        when {
            restValores.isEmpty() -> valTotal == valorPri
            valorPri > valTotal -> false
            else -> operadores.any { operador ->
                tieneSolucion(
                    operadores,
                    valTotal,
                    operador.invoke(valorPri, restValores[0]),
                    restValores.subList(1, restValores.size)
                )
            }
        }
}