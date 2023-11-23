package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hotels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerialName("cityName")
    val cityName: String,
    @SerialName("countryCode")
    val countryCode: String,
    @SerialName("stateCode")
    val stateCode: String
)