package com.roman.irtov.weathersampel.di

import android.content.Context
import com.roman.irtov.weathersampel.ui.MainActivity

interface AppProvider {
    fun provideContext(): Context
    fun inject(where: MainActivity)
}