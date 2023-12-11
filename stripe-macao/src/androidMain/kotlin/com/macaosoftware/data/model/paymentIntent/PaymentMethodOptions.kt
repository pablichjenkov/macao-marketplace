package com.macaosoftware.data.model.paymentIntent


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaymentMethodOptions(
    @SerialName("card")
    val card: Card
)