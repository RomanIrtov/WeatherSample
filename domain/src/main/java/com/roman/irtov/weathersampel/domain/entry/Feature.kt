package com.roman.irtov.weathersampel.domain.entry

data class Feature(
    val id: String,
    val type: String,
    val properties: FeaturesProperties?
)
