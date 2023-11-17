package com.roman.irtov.weathersampel.data.repository

import com.roman.irtov.weathersampel.data.network.WeatherApi
import com.roman.irtov.weathersampel.domain.entry.Feature
import com.roman.irtov.weathersampel.domain.mapper.ApiFeatureMapper
import com.roman.irtov.weathersampel.domain.repository.IWeatherRepository
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) : IWeatherRepository {
    override suspend fun getFeatures(): List<Feature> {
        return ApiFeatureMapper.map(weatherApi.getAlerts()?.features)
    }
}