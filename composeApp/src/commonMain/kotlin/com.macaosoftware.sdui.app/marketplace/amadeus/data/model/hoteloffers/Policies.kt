package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Policies(
    @SerialName("cancellations")
    val cancellations: List<Cancellation>
)