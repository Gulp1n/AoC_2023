import kotlin.time.times

fun main() {
    partTwo()
}

fun partOne() {
    val winsList: MutableList<Int> = mutableListOf()

    races.forEach { race ->
        val range = 0..race.time

        var wins: Int = 0

        range.forEach { time ->
            if (isWinner(time, race.time, race.distance)) wins += 1
        }

        winsList.add(wins)
    }

    println(winsList)

    var res: Int = winsList[0]

    for (i in 1..<winsList.size) {
        res *= winsList[i]
    }

    println(res)
}

fun partTwo() {
    val race = raceTwo
    val range = 0..race.time

    var wins: Int = 0

    range.forEach { time ->
        if (isWinner(time, race.time, race.distance)) wins += 1
    }

    println(wins)
}

fun isWinner(time: Long, maxTime: Long, distanceToBeat: Long): Boolean {
    return calculateDistance(time, maxTime) > distanceToBeat
}

fun calculateDistance(time: Long, maxTime: Long): Long {
    val speed = time
    val travelTime = maxTime-time

    return speed*travelTime
}