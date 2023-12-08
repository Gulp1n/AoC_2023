fun getData(): List<MapElement> {
    val dataList: MutableList<MapElement> = mutableListOf()

    mapData.forEach { string ->
        dataList.add(getMapElement(string))
    }

    return dataList.toList()
}

fun getMapElement(string: String): MapElement {
    val res = string.split(" = ")

    val regexLeft = """(?<=\()([A-Z]+)(?=,)""".toRegex()
    val regexRight = """(?<=,\s)([A-Z]+)(?=\))""".toRegex()

    val left = regexLeft.find(res[1])?.value ?: TODO()
    val right = regexRight.find(res[1])?.value ?: TODO()

    return MapElement(res[0], left, right)
}

data class MapElement(val name: String, val left: String, val right: String)