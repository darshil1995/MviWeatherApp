package com.weather.weatherapp.di

import com.weather.weatherapp.data.location.DefaultLocationTracker
import com.weather.weatherapp.data.repository.WeatherRepositoryImpl
import com.weather.weatherapp.domain.location.LocationTracker
import com.weather.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl) : WeatherRepository
}