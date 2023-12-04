import kotlin.math.pow

fun partOne() {
    val data = getDataPartOne()

    val pointList: MutableList<Int> = mutableListOf()

    data.forEach { numList ->

        var matches: Int = 0

        numList.receivedNumberList.forEach { number ->
            if (number in numList.winningNumberList) {
                matches += 1
            }
        }

        if (matches > 0) {
            val points: Int = 1*(2.0.pow(matches-1)).toInt()
            pointList.add(points)
        }
    }

    println(pointList.sum())
}

fun partTwo() {
    val data = getDataPartTwo()

    data.forEachIndexed { index, card ->

        var matches: Int = 0

        card.numbers.receivedNumberList.forEach { number ->

            if (number in card.numbers.winningNumberList) {
                matches += 1
            }
        }

        if (matches > 0) {
            for (i in 1..matches) {
                if (index+i < data.size) {
                    data[index+i].copies += card.copies
                }
            }
        }
    }

    val copiesCountList: MutableList<Int> = mutableListOf()

    data.forEach { card ->
        copiesCountList.add(card.copies)
    }

    println(copiesCountList.sum())
}

fun main() {
    partTwo()
}