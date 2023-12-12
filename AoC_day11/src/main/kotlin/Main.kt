import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test
import kotlin.math.abs

fun main() {
    partTwo()
}

fun partTwo() {
    val data = inputData.parse()
    val empty = data.getEmptySpace()
    val locations = data.getLocations(empty)

    val pairList: MutableList<Long> = mutableListOf()
    for (i in 0..<locations.lastIndex) {
        locations[i].getPairs(locations, i).forEach { it ->
            pairList.add(it)
        }
    }

    println(pairList.sum())
}

fun List<String>.parse() = this.map { it.toList() }

fun List<List<Char>>.getEmptySpace(): Emptiness {
    val y: MutableList<Int> = mutableListOf()
    this.forEachIndexed { i, string ->
        if ('#' !in string) y.add(i)
    }

    val x: MutableList<Int> = mutableListOf()
    this.first().indices.forEachIndexed { i, _ ->
        var valid = true
        this.forEach { it ->
            if (it[i] == '#') valid = false
        }
        if (valid) x.add(i)
    }

    return Emptiness(x.toList(), y.toList())
}

data class Emptiness(val x: List<Int>, val y: List<Int>,)

private fun List<List<Char>>.getLocations(emptiness: Emptiness): List<Location> {
    val locations: MutableList<Location> = mutableListOf()

    this.forEachIndexed { y, list ->
        list.forEachIndexed { x, char ->
            if (char == '#') {
                var xCord = x.toLong()
                var yCord = y.toLong()
                emptiness.x.forEach {
                    if (it < x) xCord += 999999
                }
                emptiness.y.forEach {
                    if (it < y) yCord += 999999
                }
                locations.add(Location(xCord, yCord))
            }
        }
    }

    return locations.toList()
}

data class Location(val x: Long, val y: Long)

private fun Location.getPairs(list: List<Location>, startNum: Int): List<Long> {
    val range = startNum+1..list.lastIndex

    val pairList: MutableList<Long> = mutableListOf()

    range.forEach { i ->
        pairList.add(calculateDistance(this, list[i]))
    }

    return pairList.toList()
}

private fun calculateDistance(first: Location, second: Location): Long {
    val deltaX: Long = abs(first.x - second.x)
    val deltaY: Long = abs(first.y - second.y)

    return deltaX+deltaY
}

private fun List<Any>.println() {
    this.forEach {
        println(it)
    }
}

class Tests {
    @Test
    fun test() {
        val data = sampleData.parse()

        val empty = data.getEmptySpace()

        val locations = data.getLocations(empty)

        assertEquals(
            Location(1000002, 0),
            locations[0]
            )
    }
}