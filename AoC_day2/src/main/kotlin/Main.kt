fun partOne() {
    val maxGreen: Int = 13
    val maxBlue: Int = 14
    val maxRed: Int = 12

    val usableDataList = getData()

    val idList: MutableList<Int> = mutableListOf()

    usableDataList.forEach {
        var valid: Boolean = true

        it.matches.forEach { item ->
            if (item.green > maxGreen) {
                valid = false
            }
            if (item.blue > maxBlue) {
                valid = false
            }
            if (item.red > maxRed) {
                valid = false
            }
        }

        if (valid) {
            idList.add(it.id)
        }
    }

    println(idList)
    println(idList.sum())
}

fun partTwo() {
    val usableDataList = getData()

    val gamePower: MutableList<Int> = mutableListOf()

    usableDataList.forEach { it ->
        val greens: MutableList<Int> = mutableListOf()
        val blues: MutableList<Int> = mutableListOf()
        val reds: MutableList<Int> = mutableListOf()

        it.matches.forEach {item ->
            greens.add(item.green)
            blues.add(item.blue)
            reds.add(item.red)
        }
        
        gamePower.add(greens.max() * blues.max() * reds.max())
    }
    
    println(gamePower.sum())
}

fun main() {
    partTwo()
}