package com.macaosoftware.data.model.remote

import com.macaosoftware.data.model.customer.Customer
import com.macaosoftware.data.model.ephemeral.Ephemeral
import com.macaosoftware.data.model.paymentIntent.PaymentIntents
import com.macaosoftware.utils.Constant.AUTHORIZATOIN
import com.macaosoftware.utils.Constant.BASE_URL
import com.macaosoftware.utils.Constant.SECRET_KEY
import com.macaosoftware.utils.Constant.STRIPE_VERSION
import com.macaosoftware.utils.Constant.STRIPE_VERSION_NU
import com.macaosoftware.utils.Constant.TIMEOUT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object StripeApiClient {
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                explicitNulls = false
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            connectTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
        }
        defaultRequest {
            headers {
                append(HttpHeaders.Authorization, "Bearer $SECRET_KEY")  // Correct way to set Authorization header
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                append("Stripe-Version", STRIPE_VERSION_NU)
            }
        }
    }

    suspend fun getCustomers(): Customer {
        val url = "https://api.stripe.com/v1/customers"
        return client.post(url).body()
    }

    suspend fun getEphemeralKey(id: String): Ephemeral {
        val url = BASE_URL + "ephemeral_keys?customer=${id}"
        return client.post(url).body()
    }

    suspend fun getPaymentIntents(customer: String, amount: Int, currency: String, automatic_payment_methods: Boolean): PaymentIntents {
        val url =
            BASE_URL + "payment_intents?customer=${customer}&amount=${amount}&currency=${currency}&automatic_payment_methods[enabled]=${automatic_payment_methods}"
        return client.post(url).body()
    }
}