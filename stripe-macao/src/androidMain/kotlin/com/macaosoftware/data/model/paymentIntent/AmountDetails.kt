package com.macaosoftware.data.model.paymentIntent


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmountDetails(
    @SerialName("tip")
    val tip: Tip
)