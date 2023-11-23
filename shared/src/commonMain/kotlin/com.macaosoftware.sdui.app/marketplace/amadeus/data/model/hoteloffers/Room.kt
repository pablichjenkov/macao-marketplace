package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Room(
    @SerialName("description")
    val description: Description,
    @SerialName("type")
    val type: String,
    @SerialName("typeEstimated")
    val typeEstimated: TypeEstimated
)