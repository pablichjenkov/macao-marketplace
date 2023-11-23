package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hotels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hotels(
    @SerialName("data")
    val hotelsData: List<Data>
)