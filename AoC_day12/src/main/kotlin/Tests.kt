import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class Tests {
    @Test
    fun pareTest() {
        assertEquals(
            RowData(
                listOf(SpringStatus.OPERATIONAL, SpringStatus.DAMAGED, SpringStatus.DAMAGED, SpringStatus.UNKNOWN, SpringStatus.UNKNOWN, SpringStatus.DAMAGED, SpringStatus.UNKNOWN, SpringStatus.DAMAGED, SpringStatus.UNKNOWN, SpringStatus.UNKNOWN, SpringStatus.UNKNOWN, SpringStatus.UNKNOWN, SpringStatus.DAMAGED, SpringStatus.UNKNOWN),
                listOf(2, 6, 1)
            ),
            inputData.parse().last()
        )
    }
}