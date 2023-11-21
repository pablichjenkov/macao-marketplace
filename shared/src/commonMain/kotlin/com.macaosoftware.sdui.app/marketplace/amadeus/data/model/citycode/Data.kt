package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("address")
    val address: Address? = null,
    @SerialName("chainCode")
    val chainCode: String? = null,
    @SerialName("dupeId")
    val dupeId: Int? = null,
    @SerialName("geoCode")
    val geoCode: GeoCode? = null,
    @SerialName("hotelId")
    val hotelId: String? = null,
    @SerialName("iataCode")
    val iataCode: String? = null,
    @SerialName("lastUpdate")
    val lastUpdate: String? = null,
    @SerialName("name")
    val name: String? = null
)