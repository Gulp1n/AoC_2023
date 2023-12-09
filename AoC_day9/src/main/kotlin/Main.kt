import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test
import kotlin.math.abs

fun main() {
    partTwo()
}

fun partTwo() {
    val res = getData().map { item ->
        getPreviousNumber(item)
    }

    println(res)
    println(res.sum())
}

fun partOne() {
    val res = getData().map { item ->
        getNextValue(item)
    }

    println(res)
    println(res.sum())
}

fun getPreviousNumber(data: List<Int>): Int {
    val list = getFirstNumbersList(data)
    var number: Int = list[0]

    for (i in 1..list.lastIndex) {
        number = list[i] - number
    }

    return number
}

fun getFirstNumbersList(data: List<Int>): List<Int> {
    val startNumber: MutableList<Int> = mutableListOf(data[0])
    var list = data

    while (true) {
        list = getDif(list)
        startNumber.add(list[0])
        if (allZeros(list)) {
            break
        }
    }

    return startNumber.toList().reversed()
}

fun getNextValue(data: List<Int>): Int {
    val endNumbers: MutableList<Int> = mutableListOf(data.last())
    var list = data

    while (true) {
        list = getDif(list)
        if (allZeros(list)) {
            break
        }
        endNumbers.add(list.last())
    }

    return endNumbers.sum()
}

fun getDif(data: List<Int>): List<Int> {
    val list: MutableList<Int> = mutableListOf()
    for (i in 1..data.lastIndex) {
        val res = (data[i]-data[i-1])
        list.add(res)
    }
    return list.toList()
}

fun allZeros(data: List<Int>): Boolean {
    val res = data.sumOf { abs(it) }
    return res == 0
}

class Tests() {
    @Test
    fun getDifTest() {
        val data = listOf(0,  3,  6,  9, 12, 15)
        val expected = listOf(3, 3, 3, 3, 3)
        val res = getDif(data)
        assertEquals(expected, res)
        val expected2 = listOf(0, 0, 0, 0)
        val res2 = getDif(res)
        assertEquals(expected2, res2)
    }

    @Test
    fun allZerosTest() {
        var data = listOf(-12, 20, -8)
        assertEquals(false, allZeros(data))
        data = listOf(0, 0, 0)
        assertEquals(true, allZeros(data))
    }

    @Test
    fun getNextValueTest() {
        var data = listOf(10, 13, 16, 21, 30, 45)
        assertEquals(68, getNextValue(data))
    }

    @Test
    fun parseStringTest() {
        val data = "13 14 13 10 5 -2 -11 -22 -35 -50 -67 -86 -107 -130 -155 -182 -211 -242 -275 -310 -347"
        val expected = listOf(13, 14, 13, 10, 5, -2, -11, -22, -35, -50, -67, -86, -107, -130, -155, -182, -211, -242, -275, -310, -347)
        assertEquals(expected, parseString(data))
    }

    @Test
    fun getPreviousNumberTest() {
        val data = listOf(10, 13, 16, 21, 30, 45)
        assertEquals(5, getPreviousNumber(data))
    }

    @Test
    fun getFirstNumberListTest() {
        val data = listOf(10, 13, 16, 21, 30, 45)
        val expected = listOf(0, 2, 0, 3, 10)
        assertEquals(expected, getFirstNumbersList(data))
    }
}