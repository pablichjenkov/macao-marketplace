package com.macaosoftware.data.model.paymentIntent


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Charges(
    @SerialName("data")
    val `data`: List<String>,
    @SerialName("has_more")
    val hasMore: Boolean,
    @SerialName("object")
    val objectX: String,
    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("url")
    val url: String
)