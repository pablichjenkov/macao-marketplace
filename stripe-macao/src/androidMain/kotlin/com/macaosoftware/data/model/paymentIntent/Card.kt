package com.macaosoftware.data.model.paymentIntent


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Card(
    @SerialName("installments")
    val installments: String?,
    @SerialName("mandate_options")
    val mandateOptions: String?,
    @SerialName("network")
    val network: String?,
    @SerialName("request_three_d_secure")
    val requestThreeDSecure: String
)