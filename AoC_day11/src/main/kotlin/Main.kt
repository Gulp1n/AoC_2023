import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test
import kotlin.math.abs

fun main() {
    partOne()
}

fun List<String>.parse(): List<List<Char>> {
    val pp: MutableList<String> = mutableListOf()
    this.forEach { string ->
        if ('#' !in string) {
            pp.add(string)
        }
        pp.add(string)
    }

    val numbers: MutableList<Int> = mutableListOf()

    for (i in 0..pp.first().lastIndex) {
        var valid = true
        pp.forEach { it ->
            if (it[i] == '#') valid = false
        }
        if (valid) numbers.add(i)
    }

    numbers.forEachIndexed { count, i ->
        pp.forEachIndexed { index, str ->
            val string = StringBuilder(str)
            string.insert(i+count, '.')
            pp[index] = string.toString()
        }
    }

    return pp.map {
        it.toList()
    }.toList()
}

fun partOne() {
    val data = inputData.parse()

    data.forEach { it ->
        println(it.toString())
    }
    val locations = data.getLocations()

    val pairList: MutableList<Int> = mutableListOf()

    for (i in 0..<locations.lastIndex) {
        locations[i].getPairs(locations, i).forEach { it ->
            pairList.add(it)
        }
    }

    println(pairList.sum())
}

private fun Location.getPairs(list: List<Location>, startNum: Int): List<Int> {
    val range = startNum+1..list.lastIndex

    val pairList: MutableList<Int> = mutableListOf()

    range.forEach { i ->
        pairList.add(
            calculateDistance(this, list[i])
        )
    }

    return pairList.toList()
}

private fun calculateDistance(first: Location, second: Location): Int {
    val deltaX = abs(first.x - second.x)
    val deltaY = abs(first.y - second.y)

    return deltaX+deltaY
}

private fun List<List<Char>>.getLocations(): List<Location> {
    val pp: MutableList<Location> = mutableListOf()
    this.forEachIndexed { y, list ->
        list.forEachIndexed { x, char ->
            if (char == '#') {
                pp.add(Location(x, y))
            }
        }
    }

    return pp.toList()
}

data class Location(val x: Int, val y: Int)

private fun List<Any>.println() {
    this.forEach {
        println(it)
    }
}

class Tests {

    @Test
    fun testParse() {
        assertEquals(
            parseTestExpected,
            sampleData.parse()
        )
    }

    @Test
    fun testGetLocations() {
        val expected = listOf(
        Location(4, 0),
        Location(9, 1),
        Location(0, 2),
        Location(8, 5),
        Location(1, 6),
        Location(12, 7),
        Location(9, 10),
        Location(0, 11),
        Location(5, 11),
        )
        
        assertEquals(expected,
            sampleData.parse().getLocations())
    }

    @Test
    fun testCalculateDistance() {
        assertEquals(
            9,
            calculateDistance(
                Location(1, 6),
                Location(5, 11)
            )
        )
    }
}