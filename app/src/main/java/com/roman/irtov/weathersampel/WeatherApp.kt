package com.roman.irtov.weathersampel

import android.app.Application
import com.roman.irtov.weathersampel.di.AppComponent
import com.roman.irtov.weathersampel.di.AppProvider

class WeatherApp : Application() {

    companion object {
        val appDependencies: AppProvider
            get() = AppComponent.get()
    }

    override fun onCreate() {
        super.onCreate()
        AppComponent.init(applicationContext)
    }
}