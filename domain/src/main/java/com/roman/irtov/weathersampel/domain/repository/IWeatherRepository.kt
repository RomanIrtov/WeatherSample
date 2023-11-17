package com.roman.irtov.weathersampel.domain.repository

import com.roman.irtov.weathersampel.domain.entry.Feature

interface IWeatherRepository {
    suspend fun getFeatures(): List<Feature>
}