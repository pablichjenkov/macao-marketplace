package com.pablichj.incubator.amadeus.endpoint.hotels

import AmadeusError
import QueryParam
import com.pablichj.incubator.amadeus.common.CallResult
import com.pablichj.incubator.amadeus.common.Envs
import com.pablichj.incubator.amadeus.common.SingleUseCase
import com.pablichj.incubator.amadeus.endpoint.accesstoken.model.AccessToken
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelsByCityResponseBody
import com.pablichj.incubator.amadeus.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HotelsByCityUseCase(
    private val dispatcher: Dispatchers,
    private val accessToken: AccessToken,
    private val cityCode: QueryParam.CityCode,
    private val radius: QueryParam.Radius,
    private val radiusUnit: QueryParam.RadiusUnit,
    private val hotelSource: QueryParam.HotelSource
) : SingleUseCase<CallResult<HotelsByCityResponseBody>> {
    override suspend fun doWork(): CallResult<HotelsByCityResponseBody> {
        return withContext(dispatcher.Unconfined) {
            try {
                val queryParams = listOf(cityCode, radius, radiusUnit, hotelSource)
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
        private val hotelsByCityUrl =
            "${Envs.TEST.hostUrl}/v1/reference-data/locations/hotels/by-city"
    }
}
