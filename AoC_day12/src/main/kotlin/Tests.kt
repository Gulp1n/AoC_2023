import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Tests {
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
        assertEquals(
            true,
            "#.#.###".validate(listOf(1,1,3))
        )
        assertEquals(
            false,
            "?#?#?#?#?#?#?#?".validate(listOf(1,3,1,6))
        )
    }

    @Test
    fun maxArrangementTest() {
        assertEquals(
            Pair(3, 8),
            "???.###".maxArrangement()
        )
    }

    @Test
    fun getPermutationCountTest() {
        assertEquals(
            4,
            getPermutationCount(RowData(
                ".??..??...?##.",
                listOf(1,1,3)
            ))
        )
        assertEquals(
            1, getPermutationCount(
                RowData(
                    "???.###",
                    listOf(1,1,3)
                )
            )
        )
        assertEquals(
            10,
            getPermutationCount(
                RowData(
                    "?###????????",
                    listOf(3,2,1)
                )
            )
        )
    }
}