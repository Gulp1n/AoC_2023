fun main() {
    partOne()
}

fun partOne() {
    val directions = directionData
    val data = getData()

    var location = data.find { it.name == "AAA" } ?: TODO()

    var searching = true
    var steps: Int = 0

    while (searching) {
        directions.forEach { direction ->
            location = getNextLocation(location, direction, data)
            steps += 1
            if (location.name == "ZZZ") {
                println(steps)
                searching = false
            }
        }
    }

    println(steps)
}

fun getNextLocation(location: MapElement, direction: Char, data: List<MapElement>): MapElement {
    if (direction == 'L') {
        val res = data.find { it.name == location.left } ?: TODO()
        return res
    }
    if (direction == 'R') {
        val res = data.find { it.name == location.right } ?: TODO()
        return res
    }
    TODO()
}