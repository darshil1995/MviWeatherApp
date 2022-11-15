package com.weather.weatherapp.domain.weather

/*
Contains Weather data per day
Map<Int, List<<WeatherData>> -->> Maps the current day index to the weather data list of that day.
                                   Weather data would represent temperature, humidity, pressure etc.

Int is current Day Index and List is for temperatures for the specific hour of that day.

Int 0 -> Today
1 -> Tomorrow and so on
 */
data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
