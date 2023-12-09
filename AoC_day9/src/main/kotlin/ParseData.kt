fun getData(): List<List<Int>> {
    return inputData.map { string ->
        parseString(string)
    }
}

fun parseString(string: String): List<Int> {
    return string.split(" ").map { it ->
        it.toInt()
    }
}