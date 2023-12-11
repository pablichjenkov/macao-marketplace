package com.macaosoftware.data.model.paymentIntent


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AutomaticPaymentMethods(
    @SerialName("allow_redirects")
    val allowRedirects: String,
    @SerialName("enabled")
    val enabled: Boolean
)