package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Price(
    @SerialName("base")
    val base: String,
    @SerialName("currency")
    val currency: String,
    @SerialName("total")
    val total: String,
    @SerialName("variations")
    val variations: Variations
)