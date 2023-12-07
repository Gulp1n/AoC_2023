
val races: List<RaceData> = listOf(
    RaceData(61, 430),
    RaceData(67, 1036),
    RaceData(75, 1307),
    RaceData(71, 1150)
)

data class RaceData(val time: Long, val distance: Long)
data class BigRaceData(val time: Long, val distance: Long)

val raceTwo = BigRaceData(61677571, 430103613071150)
