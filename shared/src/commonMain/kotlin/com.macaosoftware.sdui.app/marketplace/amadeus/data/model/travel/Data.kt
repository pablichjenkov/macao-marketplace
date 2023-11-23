package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.travel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("geoCode")
    val geoCode: GeoCode,
    @SerialName("iataCode")
    val iataCode: String,
    @SerialName("name")
    val name: String,
    @SerialName("relevance")
    val relevance: Double,
    @SerialName("subtype")
    val subtype: String,
    @SerialName("type")
    val type: String
)