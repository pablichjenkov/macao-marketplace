package com.pablichj.incubator.amadeus.endpoint.booking.hotel

import AmadeusError
import com.pablichj.incubator.amadeus.common.CallResult
import com.pablichj.incubator.amadeus.common.Envs
import com.pablichj.incubator.amadeus.common.SingleUseCase
import com.pablichj.incubator.amadeus.endpoint.booking.hotel.model.HotelBookingResponseBody
import com.pablichj.incubator.amadeus.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HotelBookingUseCase(
    private val dispatcher: Dispatchers,
    private val hotelBookingRequest: HotelBookingRequest
) : SingleUseCase<CallResult<HotelBookingResponseBody>> {

    override suspend fun doWork(): CallResult<HotelBookingResponseBody> {
        return withContext(dispatcher.Unconfined) {
            try {
                val response = httpClient.post(hotelBookingUrl) {
                    contentType(ContentType.Application.Json)
                    header(HttpHeaders.Authorization, hotelBookingRequest.accessToken.authorization)
                    setBody(hotelBookingRequest.body)
                }
                if (response.status.isSuccess()) {
                    CallResult.Success(response.body())
                } else {
                    CallResult.Error(AmadeusError.fromErrorJsonString(response.bodyAsText()))
                }
            } catch (th: Throwable) {
                th.printStackTrace()
                CallResult.Error(AmadeusError.fromException(th))
            }
        }
    }

    companion object {
        private val hotelBookingUrl = "${Envs.TEST.hostUrl}/booking/hotel-bookings"
    }
}