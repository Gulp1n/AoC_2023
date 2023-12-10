import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

fun main() {
    partOne()
}

fun partOne() {
    val data = inputData
    val start = data.getStart()

    val loop = data.mapLoop(start)

    println(loop.second)
    println(loop.first.floorDiv(2))
}

fun List<String>.mapLoop(start: Location): Pair<Int, List<Char>> {
    var location: Location = start
    var oldLocation: Location? = null
    var steps: Int = 1
    val map: MutableList<Char> = mutableListOf()

    while (true) {
        val newLocation = this.getNextMove(location, oldLocation)
        oldLocation = location
        location = newLocation
        steps += 1
        if (location.char == 'S') break
        map.add(location.char)
    }

    return Pair(steps, map.toList())
}

fun List<String>.getNextMove(location: Location, oldLocation: Location? = null): Location {
    val neighbours = this.getLocationNeighbours(location)
    val moves = location.char.getPossibleMoves()

    if (Moves.UP in moves && neighbours.up != null && neighbours.up != oldLocation) {
        val chars = listOf('|', '7', 'F', 'S')
        if (neighbours.up.char in chars) return neighbours.up
    }
    if (Moves.DOWN in moves && neighbours.down != null && neighbours.down != oldLocation) {
        val chars = listOf('|', 'L', 'J', 'S')
        if (neighbours.down.char in chars) return neighbours.down
    }
    if (Moves.LEFT in moves && neighbours.left != null && neighbours.left != oldLocation) {
        val chars = listOf('-', 'L', 'F', 'S')
        if (neighbours.left.char in chars) return neighbours.left
    }
    if (Moves.RIGHT in moves && neighbours.right != null && neighbours.right != oldLocation) {
        val chars = listOf('-', 'J', '7', 'S')
        if (neighbours.right.char in chars) return neighbours.right
    }
    TODO()
}

fun Char.getPossibleMoves(): List<Moves> {
    if (this == '|') return listOf(Moves.UP, Moves.DOWN)
    if (this == '-') return listOf(Moves.LEFT, Moves.RIGHT)
    if (this == 'L') return listOf(Moves.UP, Moves.RIGHT)
    if (this == 'J') return listOf(Moves.LEFT, Moves.UP)
    if (this == '7') return listOf(Moves.LEFT, Moves.DOWN)
    if (this == 'F') return listOf(Moves.RIGHT, Moves.DOWN)
    if (this == 'S') return listOf(Moves.UP, Moves.DOWN, Moves.LEFT, Moves.RIGHT)
    TODO()
}

fun List<String>.getLocationNeighbours(location: Location): Neighbours {
    val y = location.y
    val x = location.x
    val up = if (y > 0) { Location(x, y-1, this[y-1][x]) } else { null }
    val down = if (y < this.lastIndex) { Location(x, y+1, this[y+1][x]) } else { null }
    val left = if (x > 0) { Location(x-1, y, this[y][x-1]) } else { null }
    val right = if (x < this.lastIndex) { Location(x+1, y, this[y][x+1]) } else { null }

    return Neighbours(up, down, left, right)
}

fun List<String>.getStart(): Location {
    this.forEachIndexed { y, string ->
        val x = """S""".toRegex().find(string)?.range?.first
        if (x != null) return Location(x, y, 'S')
    }
    TODO()
}

data class Location(val x: Int, val y: Int, val char: Char)
data class Neighbours(val up: Location?, val down: Location?, val left: Location?, val right: Location?)

enum class Moves {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

class Tests {

    @Test
    fun getStartTest() {
        assertEquals(Location(53, 75, 'S'), inputData.getStart())
    }

    @Test
    fun getLocationNeighboursTest() {
        assertEquals(
        Neighbours(Location(0, 10, 'J'), Location(0, 12, '7'), null, Location(1, 11, '.')),
        inputData.getLocationNeighbours(Location(0, 11, '|'))
        )
    }

    @Test
    fun mapLoopTest() {
        val start = sampleData.getStart()
        assertEquals(
            Pair(9, listOf('|', 'L', '-', 'J', '|', '7', '-')),
            sampleData.mapLoop(start))
    }

    @Test
    fun bigTest() {
        val data = secondSampleData
        val start = data.getStart()

        val loop = data.mapLoop(start)

        println(loop.second)

        assertEquals(8, loop.first.floorDiv(2))
    }
}