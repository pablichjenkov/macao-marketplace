package com.pablichj.incubator.amadeus.endpoint.booking.flight

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class FlightBookingResponseBody(
    val data: String = "response flight booking"
) {
    fun toJson(): String = Json.encodeToString(this)

    companion object {
        fun fromJson(json: String): FlightBookingResponseBody = Json.decodeFromString(json)
    }
}



