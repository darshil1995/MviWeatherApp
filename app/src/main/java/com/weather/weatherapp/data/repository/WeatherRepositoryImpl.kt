package com.weather.weatherapp.data.repository

import com.weather.weatherapp.data.mappers.toWeatherInfo
import com.weather.weatherapp.data.remote.WeatherApi
import com.weather.weatherapp.domain.repository.WeatherRepository
import com.weather.weatherapp.domain.util.Resource
import com.weather.weatherapp.domain.weather.WeatherInfo
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: " An Unknown error Occured")
        }
    }
}