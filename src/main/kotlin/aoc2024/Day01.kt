package aoc2024

import kotlin.math.abs

class Day01(private val input: List<String>) {
    fun distance(): Int {
        val (listOne, listTwo) = devolverDosListas(input)

        val listSortedOne = listOne.sorted()
        val listSortedTwo = listTwo.sorted()

        val result = listSortedOne.zip(listSortedTwo).sumOf { (left, right) ->
            abs(left - right)
        }

        return result
    }

    fun timesValue(): Int {
        val (listOne, listTwo) = devolverDosListas(input)

        val frecuencyOfValueSecondList = listTwo.groupingBy { it }.eachCount()

        val result = listOne.sumOf { value ->
            value * (frecuencyOfValueSecondList[value] ?: 0)
        }

        return result
    }

    /**
    * Devuelve dos listas de una lista con valores separados por espacio
    */
    private fun devolverDosListas(lista : List<String>): Pair<List<Int>, List<Int>> {
        val listOne: MutableList<Int> = mutableListOf()
        val listTwo: MutableList<Int> = mutableListOf()

        for (i in lista) {
            val parts = i.split("\\s+".toRegex())
            listOne.add(parts[0].toInt())
            listTwo.add(parts[1].toInt())
        }

        return listOne to listTwo
    }
}