package com.roman.irtov.weathersampel.di.module.network

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

import com.roman.irtov.weathersampel.data.network.WeatherImageApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Singleton
class WeatherImageLoader @Inject constructor(context: Context, private val weatherImageApi: WeatherImageApi) : CoroutineScope {

    private val glide = Glide.with(context)
    private val featureByUrl = mutableMapOf<String, String>()
    private val exceptionHandler: CoroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { _, error: Throwable ->
            error.printStackTrace()
        }
    }
    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO + exceptionHandler
    private val placeholderDrawable = ContextCompat.getDrawable(context,  com.roman.irtov.weathersampel.R.drawable.ic_load_image)
    private val errorDrawable = ContextCompat.getDrawable(context,  com.roman.irtov.weathersampel.R.drawable.ic_error_load_image)
    fun loadImage(idFeature: String, imageView: ImageView) {
        if (featureByUrl.containsKey(idFeature) && featureByUrl[idFeature] != null) {
            loadImageIntoView(featureByUrl[idFeature], imageView)
        } else {
            launch {
                //TODO - Image loader required update
                //it is necessary to improve the work with streams.
                //In order to avoid repeated requests to the Image Redirect Url.
                //Also to avoid cases when imageview is used for another idFeature
                var redirectUrl = weatherImageApi.getRedirectUrl()
                if (redirectUrl != null) {
                    if (!featureByUrl.containsKey(idFeature)) {
                        featureByUrl[idFeature] = redirectUrl
                    } else {
                        featureByUrl[idFeature]?.let {
                            redirectUrl = it
                        }
                    }
                    withContext(Dispatchers.Main) {
                        loadImageIntoView(redirectUrl, imageView)
                    }
                }
            }
        }
    }

    private fun loadImageIntoView(redirectUrl: String?, imageView: ImageView) {
        glide.load(redirectUrl).override(500, 500).placeholder(placeholderDrawable).error(errorDrawable).centerInside().into(imageView)
    }


}