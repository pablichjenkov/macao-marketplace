package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.travel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Travel(
    @SerialName("data")
    val travelData: List<Data>,
    @SerialName("meta")
    val meta: Meta
)