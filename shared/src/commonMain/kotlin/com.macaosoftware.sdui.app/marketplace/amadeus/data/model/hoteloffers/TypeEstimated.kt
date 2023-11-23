package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeEstimated(
    @SerialName("bedType")
    val bedType: String,
    @SerialName("beds")
    val beds: Int,
    @SerialName("category")
    val category: String
)