package aoc2024

import java.util.PriorityQueue

class Day09(private val entrada: String) {

    private fun Int.esPar() = this % 2 == 0

    private fun crearFragmentos(): List<Int?> {
        return buildList {
            for ((indice, digito) in entrada.map { it.digitToInt() }.withIndex()) {
                val valor = if (indice.esPar()) indice / 2 else null
                repeat(digito) { add(valor) }
            }
        }
    }

    private fun defragmentar(fragmentado: List<Int?>, bloques: Int): List<Int> {
        var izquierda = 0
        var derecha = fragmentado.lastIndex
        return buildList {
            while (izquierda < bloques) {
                while (fragmentado[izquierda] != null && izquierda < derecha) add(fragmentado[izquierda++]!!)
                while (fragmentado[derecha] == null) derecha--
                izquierda++
                add(fragmentado[derecha--]!!)
            }
        }
    }

    fun resolverParte1(): Long {
        val fragmentado = crearFragmentos()
        val bloques = fragmentado.count { it != null }
        val defragmentado = defragmentar(fragmentado, bloques)
        return defragmentado.foldIndexed(0L) { indice, acumulador, valor -> acumulador + indice * valor }
    }

    private data class Block(var indice: Int, val tamano: Int, val valor: Int)

    fun resolverParte2(): Long {
        val size = 10
        val espaciosDisponibles = List(size) { PriorityQueue<Int>() }
        var posicionActual = 0

        val bloques = construirBloques(espaciosDisponibles, posicionActual)

        insertarBloquesReverso(bloques, espaciosDisponibles)

        return bloques.sumOf { it.calcularChecksum() }
    }

    private fun construirBloques(
        espacios: List<PriorityQueue<Int>>,
        indiceInicial: Int
    ): List<Block> {
        var indiceMutable = indiceInicial
        return buildList {
            for ((indiceOrden, tamanoBloque) in entrada.map(Char::digitToInt).withIndex()) {
                if (tamanoBloque > 0) {
                    if (indiceOrden.esPar()) {
                        add(Block(indiceMutable, tamanoBloque, indiceOrden / 2))
                    } else {
                        espacios[tamanoBloque].add(indiceMutable)
                    }
                    indiceMutable += tamanoBloque
                }
            }
        }
    }

    private fun insertarBloquesReverso(
        bloques: List<Block>,
        espacios: List<PriorityQueue<Int>>
    ) {
        bloques.reversed().forEach { bloque ->
            asignarEspacioABloque(bloque, espacios)
        }
    }

    private fun asignarEspacioABloque(bloque: Block, espacios: List<PriorityQueue<Int>>) {
        val (indiceEspacio, desplazamientoCumulo) = espacios.subList(bloque.tamano, espacios.size)
            .withIndex()
            .mapNotNull { (indice, espacio) ->
                espacio.peek()?.let { indiceEspacio ->
                    if (indiceEspacio < bloque.indice) {
                        indiceEspacio to indice
                    } else {
                        null
                    }
                }
            }.minByOrNull { it.first } ?: return

        val cumuloIndex = desplazamientoCumulo + bloque.tamano
        espacios[cumuloIndex].poll()
        bloque.indice = indiceEspacio
        if (bloque.tamano < cumuloIndex) {
            espacios[cumuloIndex - bloque.tamano].add(indiceEspacio + bloque.tamano)
        }
    }

    private fun Block.calcularChecksum(): Long =
        (indice.toLong() until (indice + tamano).toLong()).sumOf { it * valor }
}