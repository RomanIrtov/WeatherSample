package com.roman.irtov.weathersampel.data.network

import com.roman.irtov.weathersampel.domain.network.entry.AlertsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val DOMAIN = "https://api.weather.gov"
    }

    @GET("alerts/active")
    suspend fun getAlerts(
        @Query("status") status: String = "actual",
        @Query("message_type") messageType: String = "alert"
    ): AlertsResponse?
}