import org.testng.annotations.Test

import org.testng.Assert.*
import org.testng.AssertJUnit

class Tests {

    @Test
    fun testGetPermutationCount() {
        print(":)")
    }

    @Test
    fun pareTest() {
        assertEquals(
            RowData(
                ".##??#?#????#?",
                listOf(2, 6, 1)
            ),
            inputData.parse().last()
        )
    }

    @Test
    fun validateTest() {
        AssertJUnit.assertEquals(
            true,
            "#.#.###".validate(listOf(1, 1, 3))
        )
        AssertJUnit.assertEquals(
            false,
            "?#?#?#?#?#?#?#?".validate(listOf(1, 3, 1, 6))
        )
    }

    @Test
    fun maxAggangementTest() {
        AssertJUnit.assertEquals(
            Pair(3, 8),
            "???.###".maxArrangement()
        )
    }
}