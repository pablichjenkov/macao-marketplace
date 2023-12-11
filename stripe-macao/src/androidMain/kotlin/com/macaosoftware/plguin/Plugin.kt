package com.macaosoftware.plguin

import com.macaosoftware.data.model.customer.Customer
import com.macaosoftware.data.model.ephemeral.Ephemeral
import com.macaosoftware.data.model.paymentIntent.PaymentIntents

interface Plugin {
    suspend fun getCustomers(): Customer
    suspend fun getEphemeralKey(id: String): Ephemeral
    suspend fun getPaymentIntents(customer: String, amount: Int, currency: String, automatic_payment_methods: Boolean): PaymentIntents
}