package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hotels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoCode(
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double
)