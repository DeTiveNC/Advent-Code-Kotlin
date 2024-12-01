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