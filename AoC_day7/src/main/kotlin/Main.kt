fun main() {
    partOne()
}

fun partOne() {
    val data = getData()

    val FvoaCData: MutableList<Hand> = mutableListOf()
    val FroaCData: MutableList<Hand> = mutableListOf()
    val FHData: MutableList<Hand> = mutableListOf()
    val ToaCData: MutableList<Hand> = mutableListOf()
    val TPData: MutableList<Hand> = mutableListOf()
    val OPData: MutableList<Hand> = mutableListOf()
    val HCData: MutableList<Hand> = mutableListOf()

    data.forEach { hand ->
        hand.type = getHandType(hand)

        if (hand.type == HandTypes.FiveOfAKind) {
            FvoaCData.add(hand)
        }
        if (hand.type == HandTypes.FourOfAKind) {
            FroaCData.add(hand)
        }
        if (hand.type == HandTypes.FullHouse) {
            FHData.add(hand)
        }
        if (hand.type == HandTypes.ThreeOfAKind) {
            ToaCData.add(hand)
        }
        if (hand.type == HandTypes.TwoPair) {
            TPData.add(hand)
        }
        if (hand.type == HandTypes.OnePair) {
            OPData.add(hand)
        }
        if (hand.type == HandTypes.HighCard) {
            HCData.add(hand)
        }
    }

    val bigList = rankAllHand(FvoaCData, FroaCData, FHData, ToaCData, TPData, OPData, HCData)

    val winnings = countWinnings(bigList)

    println(winnings)
    println(winnings.sum())
}

fun countWinnings(list: List<Hand>): List<Int> {
    val winnings: MutableList<Int> = mutableListOf()

    list.forEach { hand ->
        winnings.add(hand.bet * hand.rank)
    }

    return winnings.toList()
}

fun rankAllHand(FvoaCData: List<Hand>, FroaCData: List<Hand>, FHData: List<Hand>, ToaCData: List<Hand>, TPData: List<Hand>, OPData: List<Hand>, HCData: List<Hand>): List<Hand> {
    val rankOne = rankHandSection(listOf(), HCData)
    val rankTwo = rankHandSection(rankOne, OPData)
    val rankThree = rankHandSection(rankTwo, TPData)
    val rankFour = rankHandSection(rankThree, ToaCData)
    val rankFive = rankHandSection(rankFour, FHData)
    val rankSix = rankHandSection(rankFive, FroaCData)
    val rankSeven = rankHandSection(rankSix, FvoaCData)

    return rankSeven
}

fun rankHandSection(bigArray: List<Hand>, rankingArray: List<Hand>): List<Hand> {
    val bigList: MutableList<Hand> = bigArray.toMutableList()

    val rankList: List<Char> = listOf('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A')

    rankList.forEach { first ->
        rankList.forEach { second ->
            rankList.forEach { third ->
                rankList.forEach { fourth ->
                    rankList.forEach { fifth ->
                        rankingArray.forEach { hand ->
                            val string = first.toString()+second.toString()+third.toString()+fourth.toString()+fifth.toString()

                            if (hand.string == string) {
                                bigList.add(Hand(hand.string, hand.bet, hand.type, bigList.size+1))
                            }
                        }
                    }
                }
            }
        }
    }

    return bigList.toList()
}

fun getHandType(hand: Hand): HandTypes {
    return if ('J' in hand.string) {
        getHandTypeWJ(hand)
    }
    else {
        getHandTypeWoJ(hand)
    }
}

fun getHandTypeWoJ(hand: Hand): HandTypes {
    val string = hand.string

    val charTypes: MutableList<charTypes> = mutableListOf()

    string.forEach { char ->
        var found = false
        charTypes.forEach { it ->
            if(it.char == char) {
                it.count += 1
                found = true
            }
        }

        if (!found) {
            charTypes.add(charTypes(1, char))
        }
    }

    if (charTypes.size == 1) {
        return HandTypes.FiveOfAKind
    }
    if (charTypes.size == 2) {
        if (charTypes[0].count == 1 || charTypes[0].count == 4) {
            return HandTypes.FourOfAKind
        }
        else {
            return HandTypes.FullHouse
        }
    }
    if (charTypes.size == 3) {
        charTypes.forEach { it ->
            if (it.count == 3) {
                return HandTypes.ThreeOfAKind
            }
        }
        return HandTypes.TwoPair
    }
    if (charTypes.size == 4) {
        return HandTypes.OnePair
    }
    return HandTypes.HighCard
}

fun getHandTypeWJ(hand: Hand): HandTypes {
    val string = hand.string

    val charTypes: MutableList<charTypes> = mutableListOf()

    string.forEach { char ->
        var found = false
        charTypes.forEach { it ->
            if(it.char == char) {
                it.count += 1
                found = true
            }
        }

        if (!found) {
            charTypes.add(charTypes(1, char))
        }
    }

    val jester = charTypes.find { it.char == 'J' } ?: TODO()

    if (charTypes.size <= 2) {
        return HandTypes.FiveOfAKind
    }
    charTypes.forEach {
        if (it.char != 'J') {
            val number = it.count + jester.count
            if (number == 4) {
                return HandTypes.FourOfAKind
            }
        }
    }

    charTypes.forEach {
        if (it.char != 'J') {
            val number = it.count + jester.count
            if (number == 3) {
                if (charTypes.size == 3) {
                    return HandTypes.FullHouse
                }
                else {
                    return HandTypes.ThreeOfAKind
                }
            }
        }
    }

    return HandTypes.OnePair
}

enum class HandTypes {
    FiveOfAKind,
    FourOfAKind,
    FullHouse,
    ThreeOfAKind,
    TwoPair,
    OnePair,
    HighCard
}

data class charTypes(var count: Int, val char: Char)