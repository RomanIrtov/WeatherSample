package com.roman.irtov.weathersampel.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.irtov.weathersampel.databinding.ActivityMainBinding
import com.roman.irtov.weathersampel.di.AppComponent
import com.roman.irtov.weathersampel.di.module.network.WeatherImageLoader
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var weatherImageLoader: WeatherImageLoader

    private val weatherViewModel: WeatherViewModel by viewModels { viewModelFactory }
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherAdapter: WeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppComponent.get().inject(this)
        weatherAdapter = WeatherAdapter(this, weatherImageLoader)
        binding.acMainWeatherRecycler.apply {
            adapter = weatherAdapter
            val linerLayoutManager = LinearLayoutManager(this@MainActivity)
            linerLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linerLayoutManager
        }
        binding.acMainReloadWeatherButton.setOnClickListener {
            weatherViewModel.getWeatherState()
        }
        uiObserver()
    }

    private fun uiObserver() {
        weatherViewModel.features.observe(this) { features ->
            if (weatherViewModel.isLoadingFeature.value == false && features.isEmpty()) {
                binding.acMainReloadWeatherButton.isVisible = true
                binding.acMainErrorText.isVisible = true
            } else {
                binding.acMainReloadWeatherButton.isVisible = false
                binding.acMainErrorText.isVisible = false
            }
            weatherAdapter.setItems(features)
        }

        weatherViewModel.isLoadingFeature.observe(this) { isLoadingFeature ->
            if (isLoadingFeature) {
                binding.acMainReloadWeatherButton.isVisible = false
                binding.acMainErrorText.isVisible = false
                binding.acMainProgress.isActivated = true
                binding.acMainProgress.isVisible = true
            } else {
                binding.acMainProgress.isActivated = false
                binding.acMainProgress.isVisible = false
                if (weatherViewModel.features.value?.isEmpty() == true) {
                    binding.acMainReloadWeatherButton.isVisible = true
                    binding.acMainErrorText.isVisible = true
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        weatherViewModel.getWeatherState()

    }
}