package com.macaosoftware.data.model.customer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    @SerialName("address")
    val address: String? = null,
    @SerialName("balance")
    val balance: Int? = null,
    @SerialName("created")
    val created: Int? = null,
    @SerialName("currency")
    val currency: String? = null,
    @SerialName("default_currency")
    val defaultCurrency: String? = null,
    @SerialName("default_source")
    val defaultSource: String? = null,
    @SerialName("delinquent")
    val delinquent: Boolean? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("discount")
    val discount: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("invoice_prefix")
    val invoicePrefix: String? = null,
    @SerialName("invoice_settings")
    val invoiceSettings: InvoiceSettings? = null,
    @SerialName("livemode")
    val livemode: Boolean? = null,
    @SerialName("metadata")
    val metadata: Metadata? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("next_invoice_sequence")
    val nextInvoiceSequence: Int? = null,
    @SerialName("object")
    val objectX: String? = null,
    @SerialName("phone")
    val phone: String? = null,
    @SerialName("preferred_locales")
    val preferredLocales: List<String>? = null,
    @SerialName("shipping")
    val shipping: String? = null,
    @SerialName("tax_exempt")
    val taxExempt: String? = null,
    @SerialName("test_clock")
    val testClock: String? = null
)
