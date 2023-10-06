package com.pablichj.incubator.amadeus.endpoint.accesstoken

import AmadeusError
import com.pablichj.incubator.amadeus.common.CallResult
import com.pablichj.incubator.amadeus.common.Envs
import com.pablichj.incubator.amadeus.common.SingleUseCase
import com.pablichj.incubator.amadeus.endpoint.accesstoken.model.AccessToken
import com.pablichj.incubator.amadeus.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Parameters
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAccessTokenUseCase(
    private val dispatcher: Dispatchers,
    private val getAccessTokenRequest: GetAccessTokenRequest
) : SingleUseCase<CallResult<AccessToken>> {
    override suspend fun doWork(): CallResult<AccessToken> {
        return withContext(dispatcher.Unconfined) {
            try {
                val response = httpClient.submitForm(
                    url = tokenUrl,
                    formParameters = Parameters.build {
                        getAccessTokenRequest.formParams.forEach {
                            append(it.key, it.value)
                        }
                    }
                )
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
        private val tokenUrl = "${Envs.TEST.hostUrl}/v1/security/oauth2/token"
        const val AccessTokenGrantType = "client_credentials"
    }
}