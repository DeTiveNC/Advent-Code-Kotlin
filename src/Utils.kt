import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)


/**
 * Devuelve dos listas de una lista con valores separados por espacio
 */
fun devolverDosListas(lista : List<String>): Pair<List<Int>, List<Int>> {
    val listOne: MutableList<Int> = mutableListOf()
    val listTwo: MutableList<Int> = mutableListOf()

    for (i in lista) {
        val parts = i.split("\\s+".toRegex())
        listOne.add(parts[0].toInt())
        listTwo.add(parts[1].toInt())
    }

    return listOne to listTwo
}

/**
 * Devuelve la lista de List<String> a List<Int>
 */
fun devolverInt(lista: List<String>, part: Int) : Int {
    var count = 0


    for (i in lista) {
        if (part == 1 && esSafeReportPart1(i)) {
            count++
        }
        if (part == 2 && esSafeReportPart2(i)) {
            count++
        }
    }
    return count
}

/**
 * Funcion para revisar si es Safe o no : Parte 1
 */
fun esSafeReportPart1(reporte : String): Boolean {
    var esCreciente = false
    var esDecreciente = false

    val reporteInt = reporte.split(" ").map { it.toInt() }

    for (i in 1 until reporteInt.size) {
        val diff = reporteInt[i] - reporteInt[i - 1]

        when {
            diff < -3 || diff > 3 || diff == 0 -> return false
            diff > 0 && esDecreciente -> return false
            diff < 0 && esCreciente -> return false
        }

        if (diff < 0) esDecreciente = true
        if (diff > 0) esCreciente = true
    }

    return esDecreciente || esCreciente
}

/**
 * Funcion para revisar si es Safe o no : Parte 2
 */
fun esSafeReportPart2(reporte : String): Boolean {
    val reporteInt = reporte.split(" ").map { it.toInt() }

    // Primero verificamos si ya es seguro sin modificaciones (Parte 1)
    if (esSafeReportPart1(reporte)) return true

    // Si no es seguro, probamos eliminando un nivel problemático
    for (i in reporteInt.indices) {
        val nuevaSecuencia = reporteInt.toMutableList().apply { removeAt(i) }

        // Verificamos si la nueva secuencia es segura
        if (esSafeReportPart1(nuevaSecuencia.joinToString(" "))) {
            return true
        }
    }

    // Si ninguna secuencia modificada es segura, retornamos falso
    return false
}