package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityCodeHotel(
    @SerialName("data")
    val dataList: List<Data>,
    @SerialName("meta")
    val meta: Meta? = null
)