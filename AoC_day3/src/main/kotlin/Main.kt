fun main() {
    partTwo()
}

fun partOne() {
    val regex = """[0-9]+""".toRegex()

    val partNumbers: MutableList<Int> = mutableListOf()

    for (i in inputData.indices) {
        val string = inputData[i]
        val res = regex.findAll(string)

        res.forEach { item ->

            var upperItem: String = ""
            var middleItem: String = ""
            var lowerItem: String = ""

            var rangeFirst = item.range.first
            var rangeLast = item.range.last

            if (rangeFirst > 0) rangeFirst -= 1


            if (rangeLast < string.length-1) rangeLast += 1

            val range = rangeFirst..rangeLast

            range.forEach {
                middleItem += inputData[i][it]
            }

            if (i != 0) {
                range.forEach {
                    upperItem += inputData[i-1][it]
                }
            }

            if (i != inputData.size-1) {
                range.forEach {
                    lowerItem += inputData[i+1][it]
                }
            }

            var partNumberValid: Boolean = false

            println(middleItem+upperItem+lowerItem)

            (middleItem+upperItem+lowerItem).forEach {
                if (it.code !in 48..57 && it.code != 46) {
                    partNumberValid = true
                }
            }

            if (partNumberValid) {
                partNumbers.add(item.value.toInt())
            }
        }
    }

    println(partNumbers)
    println(partNumbers.sum())
}

fun partTwo() {
    val gearRegex = """\*""".toRegex()
    val numberRegex = """[0-9]+""".toRegex()

    val gearRatios: MutableList<Int> = mutableListOf()

    for (i in inputData.indices) {
        val string = inputData[i]
        val res = gearRegex.findAll(string)

        res.forEach { item ->
            var upperItem: String = ""
            var middleItem: String = ""
            var lowerItem: String = ""

            var rangeFirst = item.range.first
            var rangeLast = item.range.last

            if (rangeFirst > 0) rangeFirst -= 1


            if (rangeLast < string.length-1) rangeLast += 1

            val range = rangeFirst..rangeLast

            range.forEach {
                middleItem += inputData[i][it]
            }

            if (i != 0) {
                range.forEach {
                    upperItem += inputData[i-1][it]
                }
            }

            if (i != inputData.size-1) {
                range.forEach {
                    lowerItem += inputData[i+1][it]
                }
            }

            val upperRes = numberRegex.findAll(upperItem)
            val middleRes = numberRegex.findAll(middleItem)
            val lowerRes = numberRegex.findAll(lowerItem)

            val numberRes = upperRes+middleRes+lowerRes

            numberRes.forEach {
                println(it.value)
            }

            if (numberRes.count() == 2) {

                val numberList: MutableList<Int> = mutableListOf()

                upperRes.forEach { number ->
                    numberRegex.findAll(inputData[i - 1]).forEach { it ->
                        if (rangeFirst+number.range.last in it.range) {
                            numberList.add(it.value.toInt())
                        }
                    }
                }

                lowerRes.forEach { number ->
                    numberRegex.findAll(inputData[i + 1]).forEach { it ->
                        if (rangeFirst+number.range.first in it.range) {
                            numberList.add(it.value.toInt())
                        }
                    }
                }

                middleRes.forEach { number ->
                    numberRegex.findAll(inputData[i]).forEach { it ->
                        if (rangeFirst+number.range.first in it.range) {
                            numberList.add(it.value.toInt())
                        }
                    }
                }

                println(numberList)

                if (numberList.size != 2) TODO("somethings very wrong here")

                gearRatios.add(numberList[0] * numberList[1])
            }
        }
    }

    println(gearRatios)
    println(gearRatios.sum())
}
