package com.roman.irtov.weathersampel.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.roman.irtov.weathersampel.ui.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface ViewModelModule {

    @Singleton
    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    fun bindsWeatherViewModel(viewModel: WeatherViewModel): ViewModel


}