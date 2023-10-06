package com.pablichj.incubator.amadeus.endpoint.offers.hotel

import AmadeusError
import QueryParam
import com.pablichj.incubator.amadeus.common.CallResult
import com.pablichj.incubator.amadeus.common.Envs
import com.pablichj.incubator.amadeus.common.SingleUseCase
import com.pablichj.incubator.amadeus.endpoint.accesstoken.model.AccessToken
import com.pablichj.incubator.amadeus.endpoint.offers.hotel.model.MultiHotelOffersResponseBody
import com.pablichj.incubator.amadeus.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MultiHotelOffersUseCase(
    private val dispatcher: Dispatchers,
    private val accessToken: AccessToken,
    private val hotelIds: QueryParam.HotelIds,
    private val adults: QueryParam.Adults,
    private val checkInDate: QueryParam.CheckInDate,
    private val roomQuantity: QueryParam.RoomQuantity,
    private val bestRateOnly: QueryParam.BestRateOnly
) : SingleUseCase<CallResult<MultiHotelOffersResponseBody>> {
    override suspend fun doWork(): CallResult<MultiHotelOffersResponseBody> {
        return withContext(dispatcher.Unconfined) {
            try {
                val queryParams = listOf(hotelIds, adults, checkInDate, roomQuantity, bestRateOnly)
                val response = httpClient.get(hotelsByCityUrl) {
                    url {
                        queryParams.forEach {
                            parameters.append(it.key, it.value)
                        }
                    }
                    header(HttpHeaders.Authorization, accessToken.authorization)
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
        private val hotelsByCityUrl = "${Envs.TEST.hostUrl}/v3/shopping/hotel-offers"
    }
}