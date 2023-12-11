package com.macaosoftware.utils.usecases

import com.macaosoftware.data.model.customer.Customer
import com.macaosoftware.data.model.ephemeral.Ephemeral
import com.macaosoftware.data.model.paymentIntent.PaymentIntents

sealed class PaymentIntentState {
    object LOADING : PaymentIntentState()
    data class SUCCESS(val paymentIntents: PaymentIntents) : PaymentIntentState()
    data class ERROR(val error: String) : PaymentIntentState()
}