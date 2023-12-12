import kotlin.math.pow
import kotlin.text.StringBuilder

fun main() {
    partOne()
}

fun partOne() {
    val data = inputData.parse()

    val count = data.map { getPermutationCount(it) }

    count.println()
    println(count.sum())
}

fun getPermutationCount(data: RowData): Int {
    val max = data.map.maxArrangement()

    val unknowns = """\?""".toRegex().findAll(data.map).toList()

    var count = 0

    for (i in 0..<max.second) {
        val string = StringBuilder(data.map)
        val binary = StringBuilder(i.toString(2))
        while (binary.length < max.first) {
            binary.insert(0, '0')
        }

        unknowns.forEachIndexed { index, it ->
            if (binary[index] == '0') {
                string.setCharAt(it.range.first, '.')
            }
            else {
                string.setCharAt(it.range.first, '#')
            }
        }

        if (string.toString().validate(data.numMap)) count += 1
    }

    if (count == 307) println(data)

    return count
}

fun String.maxArrangement(): Pair<Int, Int> {
    val num = """\?""".toRegex().findAll(this).count()
    return Pair(num, 2.0.pow(num).toInt())
}

fun String.validate(list: List<Int>): Boolean {
    val res = """#+""".toRegex().findAll(this).toList()
    if (res.size != list.size) return false
    val count = res.map { it.value.length }
    return list == count
}

private fun List<Any>.println() {
    this.forEach {
        println(it)
    }
}
