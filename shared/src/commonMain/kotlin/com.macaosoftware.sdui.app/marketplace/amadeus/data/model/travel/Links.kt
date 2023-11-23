package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.travel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    @SerialName("self")
    val self: String
)