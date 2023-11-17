package com.roman.irtov.weathersampel.domain.network.entry

import com.google.gson.annotations.SerializedName

data class AlertsResponse(
    @SerializedName("features")
    val features: List<ApiFeature>?
)