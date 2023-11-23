package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hotels


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("address")
    val address: Address,
    @SerialName("geoCode")
    val geoCode: GeoCode,
    @SerialName("hotelIds")
    val hotelIds: List<String>,
    @SerialName("iataCode")
    val iataCode: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("relevance")
    val relevance: Int,
    @SerialName("subType")
    val subType: String,
    @SerialName("type")
    val type: String
)