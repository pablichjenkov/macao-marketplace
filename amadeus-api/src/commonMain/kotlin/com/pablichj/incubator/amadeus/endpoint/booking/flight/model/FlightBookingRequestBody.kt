package com.pablichj.incubator.amadeus.endpoint.booking.flight.model

@kotlinx.serialization.Serializable
data class FlightBookingRequestBody (
    val name: String = "booking a flight"
)
