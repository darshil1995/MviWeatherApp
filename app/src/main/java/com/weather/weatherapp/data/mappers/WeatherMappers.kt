package com.weather.weatherapp.data.mappers

import com.weather.weatherapp.data.remote.WeatherDataDto
import com.weather.weatherapp.data.remote.WeatherDto
import com.weather.weatherapp.domain.weather.WeatherData
import com.weather.weatherapp.domain.weather.WeatherInfo
import com.weather.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/* Will have extension function to map one type  to another
             i.e WeatherData To WeatherDataDTO
*/


private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {

    return time.mapIndexed { index: Int, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        /* Since each day contains 24 hours we will have 24 temperature data for each day
        *  so it will map temperature by the day.
        */

        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }.also { println(it.keys) }.also { println(it.values) }
}


fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    // 0 represents to current day
    val currentWeatherData = weatherDataMap[0]?.find {
        // If time  is 1:20 then it will take previous hour( i.e 1) and if its more than 30 minute
        // it will take the next hour(i.e 2)
        val hour = if (now.minute < 30) now.hour else now.hour + 1

        it.time.hour == hour
    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}