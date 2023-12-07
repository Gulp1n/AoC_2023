fun getData(): List<Hand> {
    val data: MutableList<Hand> = mutableListOf()

    inputData.forEach { string ->
        data.add(getHand(string))
    }

    return data.toList()
}

fun getHand(string: String): Hand {
    val res = string.split(" ")

    val bet = res[1].toInt()

    return Hand(res[0], bet)
}

data class Hand(val string: String, val bet: Int, var type: HandTypes = HandTypes.HighCard, var rank: Int = 1)