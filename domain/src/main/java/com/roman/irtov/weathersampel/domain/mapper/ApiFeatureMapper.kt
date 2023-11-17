package com.roman.irtov.weathersampel.domain.mapper

import android.annotation.SuppressLint
import com.roman.irtov.weathersampel.domain.entry.Feature
import com.roman.irtov.weathersampel.domain.entry.FeaturesProperties
import com.roman.irtov.weathersampel.domain.network.entry.ApiFeature
import com.roman.irtov.weathersampel.domain.network.entry.ApiFeaturesProperties
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

object ApiFeatureMapper {

    fun map(list: List<ApiFeature>?): List<Feature> {
        return list?.map {
            it.toFeature()
        } ?: listOf()
    }

    private fun ApiFeature.toFeature(): Feature {
        return Feature(id = id, type = type, properties = properties?.toProperties())
    }

    private fun ApiFeaturesProperties.toProperties(): FeaturesProperties {
        return FeaturesProperties(
            _id = _id, type = type, id = id, event = event, effective = convertDate(effective), ends = convertDate(ends), senderName = senderName
        )
    }

    @SuppressLint("ConstantLocale")
    private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
    private fun convertDate(date: String?): Long {
        try {
            if (date != null) {
                return dateFormat.parse(date)?.time ?: 0L
            }
        } catch (e: ParseException) {
            e.printStackTrace()

        }
        return 0L
    }

}