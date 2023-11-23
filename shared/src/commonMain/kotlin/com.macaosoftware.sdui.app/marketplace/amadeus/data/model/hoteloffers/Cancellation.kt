package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cancellation(
    @SerialName("amount")
    val amount: String,
    @SerialName("deadline")
    val deadline: String
)