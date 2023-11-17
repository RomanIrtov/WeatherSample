package com.roman.irtov.weathersampel.domain.network.entry

data class ApiFeature(
    val id: String,
    val type: String,
    val properties: ApiFeaturesProperties?
)
