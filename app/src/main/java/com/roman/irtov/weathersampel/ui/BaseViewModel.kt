package com.roman.irtov.weathersampel.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

open class BaseViewModel : ViewModel() {
    //The base class can be used to integrate ViewState EventState
    //ViewState will require watcher integration and will not be used for demonstration

    protected val viewScope = CoroutineScope(Dispatchers.Main)



}