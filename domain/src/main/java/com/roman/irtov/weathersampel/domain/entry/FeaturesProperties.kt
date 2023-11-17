package com.roman.irtov.weathersampel.domain.entry

import com.google.gson.annotations.SerializedName

data class FeaturesProperties(
    @SerializedName("@id")
    val _id: String,
    @SerializedName("@type")
    val type: String,
    @SerializedName("id")
    val id: String,
    val event: String,
    val effective: Long,
    val ends: Long,
    val senderName: String
)

