fun List<String>.parse(): List<RowData> {
    return this.map { string ->
        val res = string.split(" ")
        val numbers = """[0-9"]""".toRegex().findAll(res[1]).toList() ?: TODO()
        val map = res[0].map {
            when (it) {
                '.' -> SpringStatus.OPERATIONAL
                '#' -> SpringStatus.DAMAGED
                '?' -> SpringStatus.UNKNOWN
                else -> TODO()
            }
        }

        RowData(
            map,
            numbers.map { it.value.toInt() }
        )
    }
}

data class RowData(val map: List<SpringStatus>, val numMap: List<Int>)

enum class SpringStatus {
    OPERATIONAL,
    DAMAGED,
    UNKNOWN
}