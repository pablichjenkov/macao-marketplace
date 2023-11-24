package com.macaosoftware.sdui.app.marketplace.amadeus.data.remote

<<<<<<< HEAD
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.CityCodeHotel
=======
>>>>>>> origin
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers.HotelOffers
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hotels.Hotels
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.travel.Travel
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util.Authorization
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util.Token
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
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object KtorClientApi {
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                explicitNulls = true
                coerceInputValues = false
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }

        install(HttpTimeout) {
            val timeout = 300000L
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
            connectTimeoutMillis = timeout
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
        defaultRequest {
            headers {
                header(Authorization, Token)
            }
        }
    }

    suspend fun getHotelOffers(hotelId: String): HotelOffers {
        val url = "https://test.api.amadeus.com/v3/shopping/hotel-offers?hotelIds=${hotelId}"
        return client.get(url).body()
    }

<<<<<<< HEAD
    suspend fun getHotelOffers(hotelId: String): HotelOffers {
        val url = "https://test.api.amadeus.com/v3/shopping/hotel-offers?hotelIds=${hotelId}"
        return client.get(url).body()
    }

=======
>>>>>>> origin
    suspend fun getHotels(): Hotels {
        val url = "https://test.api.amadeus.com/v1/reference-data/locations/hotel?keyword=Star&subType=HOTEL_LEISURE&subType=HOTEL_GDS&countryCode=US&lang=EN&max=20"
        return client.get(url).body()
    }


    suspend fun getTravel(): Travel{
        val url = "https://test.api.amadeus.com/v1/reference-data/recommended-locations?cityCodes=PAR%2CNYK&travelerCountryCode=FR"
        return client.get(url).body()
    }


}