package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Variations(
    @SerialName("average")
    val average: Average,
    @SerialName("changes")
    val changes: List<Change>
)