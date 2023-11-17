package com.roman.irtov.weathersampel.data.network

import com.roman.irtov.weathersampel.domain.network.entry.IWeatherImageApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherImageApi @Inject constructor() : IWeatherImageApi {
    companion object {
        private const val PHOTO_BASE_URL = "https://picsum.photos/1000"
    }

    override suspend fun getRedirectUrl(): String? {
        var redirectUrl: String? = null
        withContext(Dispatchers.IO) {
            var httpURLConnection: HttpURLConnection? = null
            try {
                val url = URL(PHOTO_BASE_URL)
                httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.instanceFollowRedirects = false
                httpURLConnection.doInput = false
                httpURLConnection.doOutput = false
                redirectUrl = httpURLConnection.getHeaderField("Location")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                httpURLConnection?.disconnect()
            }
        }
        return redirectUrl
    }

}