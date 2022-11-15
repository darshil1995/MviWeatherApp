package com.weather.weatherapp.domain.repository

import com.weather.weatherapp.domain.util.Resource
import com.weather.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}