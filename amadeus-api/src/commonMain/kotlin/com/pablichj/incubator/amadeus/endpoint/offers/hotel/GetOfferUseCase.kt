package com.pablichj.incubator.amadeus.endpoint.offers.hotel

import AmadeusError
import com.pablichj.incubator.amadeus.common.CallResult
import com.pablichj.incubator.amadeus.common.Envs
import com.pablichj.incubator.amadeus.common.SingleUseCase
import com.pablichj.incubator.amadeus.endpoint.offers.hotel.model.GetOfferResponseBody
import com.pablichj.incubator.amadeus.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.appendEncodedPathSegments
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetOfferUseCase(
    private val dispatcher: Dispatchers,
    private val getOfferRequest: GetOfferRequest
) : SingleUseCase<CallResult<GetOfferResponseBody>> {
    override suspend fun doWork(): CallResult<GetOfferResponseBody> {
        return withContext(dispatcher.Unconfined) {
            try {
                val response = httpClient.get(getOfferUrl) {
                    url {
                        appendEncodedPathSegments(
                            "/hotel-offers/${getOfferRequest.offerId}"
                        )
                    }
                    header(HttpHeaders.Authorization, getOfferRequest.accessToken.authorization)
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
        private val getOfferUrl = "${Envs.TEST.hostUrl}/v3/shopping"
    }
}