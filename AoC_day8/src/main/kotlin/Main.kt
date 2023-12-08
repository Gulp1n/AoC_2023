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

    val locationList = getStartingLocations(data)
    val stepsList: MutableList<Int> = mutableListOf()

    println(locationList)

    locationList.forEach { location ->
        stepsList.add(calculateSteps(location, directions, data))
    }

    println(stepsList)

    val steps = calculateBigLCM(stepsList)
    println(steps)
}

fun calculateBigLCM(list: List<Int>): Long {
    var number: Long = list[0].toLong()
    for (i in 1..<list.size) {
        println(i)
        number = getLCM(number, list[i].toLong())
    }
    return number
}

fun getLCM(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}

fun calculateSteps(locationPoint: MapElement, directions: String, data: List<MapElement>): Int {
    var location = locationPoint

    var searching = true
    var steps: Int = 0

    while (searching) {
        directions.forEach { direction ->
            location = getNextLocation(location, direction, data)
            steps += 1
            if (location.name.last() == 'Z') {
                searching = false
                return steps
            }
        }
    }
    return steps
}

fun partTwoOld() {
    val directions = directionData
    val data = getData()

    var locationList = getStartingLocations(data)

    println(locationList)

    var searching = true
    var steps: Long = 0

    while (searching) {
        directions.forEach { direction ->
            locationList = getAllNextLocations(locationList, direction, data)
            steps += 1
            if (endReached(locationList)) {
                searching = false
                println(steps)
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
        if (location.name[2] != 'Z') {
            return false
        }
    }

    return true
}