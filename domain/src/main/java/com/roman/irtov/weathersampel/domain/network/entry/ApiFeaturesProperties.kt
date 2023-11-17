package com.roman.irtov.weathersampel.domain.network.entry

import com.google.gson.annotations.SerializedName

data class ApiFeaturesProperties(
    @SerializedName("@id")
    val _id: String,
    @SerializedName("@type")
    val type: String,
    @SerializedName("id")
    val id: String,
    val event: String,
    val effective: String?,
    val ends: String?,
    val senderName: String
)

