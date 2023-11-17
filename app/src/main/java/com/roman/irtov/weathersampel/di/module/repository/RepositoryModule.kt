package com.roman.irtov.weathersampel.di.module.repository

import com.roman.irtov.weathersampel.data.repository.WeatherRepository
import com.roman.irtov.weathersampel.domain.repository.IWeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideWeatherRepository(weatherRepository: WeatherRepository): IWeatherRepository

}