fun main() {
    partTwo()
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
                searching = false
            }
        }
    }

    println(steps)
}

fun partTwo() {
    val directions = directionData
    val data = getData()

    var locationList = getStartingLocations(data)

    var searching = true
    var steps = 0

    while (searching) {
        directions.forEach { direction ->
            locationList = getAllNextLocations(locationList, direction, data)
            steps += 1
            if (endReached(locationList)) {
                searching = false
            }
        }
    }

    println(steps)
}

fun getAllNextLocations(locationList: List<MapElement>, direction: Char, data: List<MapElement>): List<MapElement> {
    val list: MutableList<MapElement> = mutableListOf()

    locationList.forEach { location ->
        list.add(getNextLocation(location, direction, data))
    }

    return list.toList()
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

fun getStartingLocations(data: List<MapElement>): List<MapElement> {
    val startList: MutableList<MapElement> = mutableListOf()

    data.forEach { step ->
        if (step.name.last() == 'A') {
            startList.add(step)
        }
    }

    return startList.toList()
}

fun endReached(locations: List<MapElement>): Boolean {
    locations.forEach { location ->
        if (location.name.last() != 'Z') {
            return false
        }
    }

    return true
}