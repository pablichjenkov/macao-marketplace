package com.macaosoftware.data.model.paymentIntent


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaymentIntents(
    @SerialName("amount")
    val amount: Int,
    @SerialName("amount_capturable")
    val amountCapturable: Int,
    @SerialName("amount_details")
    val amountDetails: AmountDetails,
    @SerialName("amount_received")
    val amountReceived: Int,
    @SerialName("application")
    val application: String?,
    @SerialName("application_fee_amount")
    val applicationFeeAmount: String?,
    @SerialName("automatic_payment_methods")
    val automaticPaymentMethods: AutomaticPaymentMethods,
    @SerialName("canceled_at")
    val canceledAt: String?,
    @SerialName("cancellation_reason")
    val cancellationReason: String?,
    @SerialName("capture_method")
    val captureMethod: String,
    @SerialName("charges")
    val charges: Charges,
    @SerialName("client_secret")
    val clientSecret: String,
    @SerialName("confirmation_method")
    val confirmationMethod: String,
    @SerialName("created")
    val created: Int,
    @SerialName("currency")
    val currency: String,
    @SerialName("customer")
    val customer: String,
    @SerialName("description")
    val description: String?,
    @SerialName("id")
    val id: String,
    @SerialName("invoice")
    val invoice: String?,
    @SerialName("last_payment_error")
    val lastPaymentError: String?,
    @SerialName("latest_charge")
    val latestCharge: String?,
    @SerialName("livemode")
    val livemode: Boolean,
    @SerialName("metadata")
    val metadata: Metadata,
    @SerialName("next_action")
    val nextAction: String?,
    @SerialName("object")
    val objectX: String,
    @SerialName("on_behalf_of")
    val onBehalfOf: String?,
    @SerialName("payment_method")
    val paymentMethod: String?,
    @SerialName("payment_method_configuration_details")
    val paymentMethodConfigurationDetails: PaymentMethodConfigurationDetails,
    @SerialName("payment_method_options")
    val paymentMethodOptions: PaymentMethodOptions,
    @SerialName("payment_method_types")
    val paymentMethodTypes: List<String>,
    @SerialName("processing")
    val processing: String?,
    @SerialName("receipt_email")
    val receiptEmail: String?,
    @SerialName("review")
    val review: String?,
    @SerialName("setup_future_usage")
    val setupFutureUsage: String?,
    @SerialName("shipping")
    val shipping: String?,
    @SerialName("source")
    val source: String?,
    @SerialName("statement_descriptor")
    val statementDescriptor: String?,
    @SerialName("statement_descriptor_suffix")
    val statementDescriptorSuffix: String?,
    @SerialName("status")
    val status: String,
    @SerialName("transfer_data")
    val transferData: String?,
    @SerialName("transfer_group")
    val transferGroup: String?
)