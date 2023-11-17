package com.roman.irtov.weathersampel.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roman.irtov.weathersampel.domain.entry.Feature
import com.roman.irtov.weathersampel.domain.repository.IWeatherRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherViewModel @Inject constructor(val weatherRepository: IWeatherRepository) : BaseViewModel() {


    //For demonstration, a simplification is used without viewState and eventState
    private val _features: MutableLiveData<List<Feature>> = MutableLiveData<List<Feature>>(listOf())
    val features: LiveData<List<Feature>> = _features
    private val _isLoadingFeature: MutableLiveData<Boolean> = MutableLiveData<Boolean>(true)
    val isLoadingFeature: LiveData<Boolean> = _isLoadingFeature

    private val exceptionHandler: CoroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { _, error: Throwable ->
            error.printStackTrace()
            viewScope.launch {
                _isLoadingFeature.value = false
            }
        }
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO + exceptionHandler)

    fun getWeatherState() {
        _isLoadingFeature.value = true
        scope.launch {
            withContext(viewScope.coroutineContext) {
                _features.value = weatherRepository.getFeatures()
                _isLoadingFeature.value = false
            }
        }
    }
}