package com.pablichj.incubator.amadeus.endpoint.booking.flight

import com.pablichj.incubator.amadeus.endpoint.accesstoken.model.AccessToken
import com.pablichj.incubator.amadeus.endpoint.booking.flight.model.FlightBookingRequestBody

class FlightBookingRequest(
    val accessToken: AccessToken,
    val body: FlightBookingRequestBody
)