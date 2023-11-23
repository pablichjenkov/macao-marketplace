package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RateFamilyEstimated(
    @SerialName("code")
    val code: String,
    @SerialName("type")
    val type: String
)