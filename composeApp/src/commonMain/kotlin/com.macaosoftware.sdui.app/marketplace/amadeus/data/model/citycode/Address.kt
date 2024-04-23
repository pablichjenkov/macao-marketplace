package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerialName("countryCode")
    val countryCode: String? = null
)