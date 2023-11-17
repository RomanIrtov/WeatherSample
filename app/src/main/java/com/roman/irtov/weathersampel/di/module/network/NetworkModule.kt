package com.roman.irtov.weathersampel.di.module.network

import com.google.gson.Gson
import com.roman.irtov.weathersampel.data.network.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.time.Duration
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideGson(): Gson = Gson()
    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .readTimeout(Duration.ofSeconds(10))
            .writeTimeout(Duration.ofSeconds(10))
    }
    @Provides
    @Singleton
    fun provideBoxApi(clientBuilder: OkHttpClient.Builder, gson: Gson): WeatherApi {
        return Retrofit.Builder()
            .client(clientBuilder.build())
            .baseUrl(WeatherApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create()
    }

}