package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Change(
    @SerialName("base")
    val base: String,
    @SerialName("endDate")
    val endDate: String,
    @SerialName("startDate")
    val startDate: String
)