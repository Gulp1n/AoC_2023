fun List<String>.parse(): List<RowData> {
    return this.map { string ->
        val res = string.split(" ")
        val numbers = """[0-9"]+""".toRegex().findAll(res[1]).toList()

        RowData(
            res[0],
            numbers.map { it.value.toInt() }
        )
    }
}

data class RowData(val map: String, val numMap: List<Int>)