package com.roman.irtov.weathersampel.domain.network.entry

interface IWeatherImageApi {
    suspend fun getRedirectUrl(): String?
}