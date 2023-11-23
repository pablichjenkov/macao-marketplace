package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.travel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("count")
    val count: Int,
    @SerialName("links")
    val links: Links
)