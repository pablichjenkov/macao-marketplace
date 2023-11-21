package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoCode(
    @SerialName("latitude")
    val latitude: Double? = null,
    @SerialName("longitude")
    val longitude: Double? = null
)