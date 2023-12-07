fun main() {
    partTwo()
}

fun partOne() {
    val seedToSoil = convertMapList(seedToSoilList)
    val soilToFertilizer = convertMapList(soilToFertilizerList)
    val fertilizerToWater = convertMapList(fertilizerToWaterList)
    val waterToLight = convertMapList(waterToLightList)
    val lightToTemperature = convertMapList(lightToTemperature)
    val temperatureToHumidity = convertMapList(temperatureToHumidityList)
    val humidityToLocation = convertMapList(humidityToLocationList)

    val locationList: MutableList<Long> = mutableListOf()

    seeds.forEach { seed ->
        val soil = getDestination(seed, seedToSoil)
        val fertilizer = getDestination(soil, soilToFertilizer)
        val water = getDestination(fertilizer, fertilizerToWater)
        val light = getDestination(water, waterToLight)
        val temperature = getDestination(light, lightToTemperature)
        val humidity = getDestination(temperature, temperatureToHumidity)
        val location = getDestination(humidity, humidityToLocation)

        locationList.add(location)
    }

    println(locationList.min())
}

fun convertMapList(list: List<MapClass>): List<MapRanges> {
    val rangeList: MutableList<MapRanges> = mutableListOf()

    list.forEach { it ->
        val desRange = it.destinationRangeStart..<(it.destinationRangeStart+it.rangeLength)
        val srcRange = it.sourceRangeStart..<(it.sourceRangeStart+it.rangeLength)

        rangeList.add(MapRanges(desRange, srcRange))
    }

    return rangeList.toList()
}

fun getDestination(source: Long, rangeList: List<MapRanges>): Long {
    rangeList.forEach { it ->
        if (source in it.sourceRange) {
            val offset = source-it.sourceRange.first
            return it.destinationRange.first+offset
        }
    }

    return source
}

data class MapRanges(val destinationRange: LongRange, val sourceRange: LongRange)

fun partTwo() {
    val seedToSoil = convertMapList(seedToSoilList)
    val soilToFertilizer = convertMapList(soilToFertilizerList)
    val fertilizerToWater = convertMapList(fertilizerToWaterList)
    val waterToLight = convertMapList(waterToLightList)
    val lightToTemperature = convertMapList(lightToTemperature)
    val temperatureToHumidity = convertMapList(temperatureToHumidityList)
    val humidityToLocation = convertMapList(humidityToLocationList)

    var lowestLocation: Long = Long.MAX_VALUE

    seedsPartTwo.forEach { seedsRange ->
        convertSeedRange(seedsRange).forEach { seed ->
            val soil = getDestination(seed, seedToSoil)
            val fertilizer = getDestination(soil, soilToFertilizer)
            val water = getDestination(fertilizer, fertilizerToWater)
            val light = getDestination(water, waterToLight)
            val temperature = getDestination(light, lightToTemperature)
            val humidity = getDestination(temperature, temperatureToHumidity)
            val location = getDestination(humidity, humidityToLocation)

            if (location < lowestLocation) {
                lowestLocation = location
            }
        }
    }

    println(lowestLocation)
}

fun convertSeedRange(range: SeedRange): LongRange {
    return range.seedRangeStart..<range.seedRangeStart + range.rangeLength
}