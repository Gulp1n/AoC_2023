
fun getDataPartOne(): List<NumberLists> {
    val gameDataList: MutableList<NumberLists> = mutableListOf()

    Data.forEach { string ->
        gameDataList.add(getNumberLists(string))
    }

    return gameDataList.toList()
}

fun getDataPartTwo(): List<Card> {
    val gameDataList: MutableList<Card> = mutableListOf()

    Data.forEach { string ->
        gameDataList.add(getCard(string))
    }

    return gameDataList.toList()
}

fun getNumberLists(string: String): NumberLists {
    val winningNumberRegex = """:.*\|""".toRegex()
    val receivedNumberRegex = """\|.*;""".toRegex()
    val numberRegex = """[0-9]+""".toRegex()

    val winningNumberStringRes = winningNumberRegex.find(string) ?: TODO()
    val receivedNumberStringRes = receivedNumberRegex.find(string) ?: TODO()

    val winningNumberRes = numberRegex.findAll(winningNumberStringRes.value)
    val receivedNumberRes = numberRegex.findAll(receivedNumberStringRes.value)

    val winningNumberList: MutableList<Int> = mutableListOf()
    val receivedNumberList: MutableList<Int> = mutableListOf()

    winningNumberRes.forEach { res ->
        winningNumberList.add(res.value.toInt())
    }

    receivedNumberRes.forEach { res ->
        receivedNumberList.add(res.value.toInt())
    }

    return NumberLists(winningNumberList, receivedNumberList)
}

fun getCard(string: String): Card {
    return Card(getNumberLists(string))
}

data class Card(val numbers: NumberLists, var copies: Int = 1)

data class NumberLists(val winningNumberList: List<Int>, val receivedNumberList: List<Int>)