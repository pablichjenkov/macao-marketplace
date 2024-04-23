package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("links")
    val links: Links? = null
)