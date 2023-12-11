package com.macaosoftware.data.model.customer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InvoiceSettings(
    @SerialName("custom_fields")
    val customFields: String?=null,
    @SerialName("default_payment_method")
    val defaultPaymentMethod: String? =null,
    @SerialName("footer")
    val footer: String?= null,
    @SerialName("rendering_options")
    val renderingOptions: String? = null
)