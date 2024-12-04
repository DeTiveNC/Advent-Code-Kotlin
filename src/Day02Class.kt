class Day02Class(private val input: List<String>) {
    fun obtenerSafeCount(part: Int): Int {
        return devolverInt(part)
    }

    private fun devolverInt(part: Int): Int {
        var count = 0
        for (i in input) {
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
    private fun esSafeReportPart1(reporte : String): Boolean {
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
    private fun esSafeReportPart2(reporte : String): Boolean {
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
}