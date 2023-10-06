package com.pablichj.incubator.amadeus.endpoint.city

import AmadeusError
import QueryParam
import com.pablichj.incubator.amadeus.common.CallResult
import com.pablichj.incubator.amadeus.common.Envs
import com.pablichj.incubator.amadeus.common.SingleUseCase
import com.pablichj.incubator.amadeus.endpoint.accesstoken.model.AccessToken
import com.pablichj.incubator.amadeus.endpoint.city.model.CitySearchResponseBody
import com.pablichj.incubator.amadeus.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CitySearchUseCase(
    private val dispatcher: Dispatchers,
    private val accessToken: AccessToken,
    private val countryCode: QueryParam.CountryCode,
    private val keyword: QueryParam.Keyword,
    private val max: QueryParam.Max
) : SingleUseCase<CallResult<CitySearchResponseBody>> {
    override suspend fun doWork(): CallResult<CitySearchResponseBody> {
        return withContext(dispatcher.Unconfined) {
            try {
                val queryParams = listOf(countryCode, keyword, max)
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
        private val hotelsByCityUrl = "${Envs.TEST.hostUrl}/v1/reference-data/locations/cities"
    }
}