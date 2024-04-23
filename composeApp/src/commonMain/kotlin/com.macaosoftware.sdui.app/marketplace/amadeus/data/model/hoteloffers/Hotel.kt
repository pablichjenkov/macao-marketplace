package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hotel(
    @SerialName("chainCode")
    val chainCode: String,
    @SerialName("cityCode")
    val cityCode: String,
    @SerialName("dupeId")
    val dupeId: String,
    @SerialName("hotelId")
    val hotelId: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String
)