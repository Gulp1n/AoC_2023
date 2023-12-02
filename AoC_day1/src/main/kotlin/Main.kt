
fun extractNumber(penis: String): Double {
    var pp = penis
    val asciiNumbers = 48..57
    val dict = mapOf<String, String>("one" to "one1one", "two" to "two2two", "three" to "three3three", "four" to "four4four", "five" to "five5five", "six" to "six6six", "seven" to "seven7seven", "eight" to "eight8eight", "nine" to "nine9nine")
    val intList: MutableList<Int> = mutableListOf()

    dict.forEach {
        if (it.key in pp) {
            pp = pp.replace(it.key, it.value)
        }
    }

    pp.forEach {
        if (it.code in asciiNumbers) {
            intList.add(it.toString().toInt())
        }
    }

    var num: Double = 0.0

    num += intList[0] * 10
    num += intList.last()


    return num
}

fun main() {
    val bigNumList = bigList.map {
        extractNumber(it)
    }

    println(bigNumList.sum().toInt())
}