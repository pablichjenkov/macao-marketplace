package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("available")
    val available: Boolean,
    @SerialName("hotel")
    val hotel: Hotel,
    @SerialName("offers")
    val offers: List<Offer>,
    @SerialName("self")
    val self: String,
    @SerialName("type")
    val type: String
)