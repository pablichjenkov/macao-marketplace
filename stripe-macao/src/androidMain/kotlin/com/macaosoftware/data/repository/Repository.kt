package com.macaosoftware.data.repository

import com.macaosoftware.data.model.customer.Customer
import com.macaosoftware.data.model.ephemeral.Ephemeral
import com.macaosoftware.data.model.paymentIntent.PaymentIntents
import com.macaosoftware.data.model.remote.StripeApiClient
import com.macaosoftware.plguin.Plugin

class Repository : Plugin {

    override suspend fun getCustomers(): Customer {
        return StripeApiClient.getCustomers()
    }

    override suspend fun getEphemeralKey(id: String): Ephemeral {
        return StripeApiClient.getEphemeralKey(id)
    }

    override suspend fun getPaymentIntents(
        customer: String,
        amount: Int,
        currency: String,
        automatic_payment_methods: Boolean
    ): PaymentIntents {
        return StripeApiClient.getPaymentIntents(
            customer = customer,
            amount = amount,
            currency = currency,
            automatic_payment_methods = automatic_payment_methods
        )
    }
}