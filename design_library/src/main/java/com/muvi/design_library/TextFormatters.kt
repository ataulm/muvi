package com.muvi.design_library

/**
 * Formats as a comma-separated list, with the last item appended with an ampersand.
 */
fun List<String>.commaAmpersandList() = when (size) {
    0 -> null
    1 -> this[0]
    else -> {
        val firstLot = subList(0, size - 1).joinToString(separator = ", ")
        firstLot + " & " + last()
    }
}
