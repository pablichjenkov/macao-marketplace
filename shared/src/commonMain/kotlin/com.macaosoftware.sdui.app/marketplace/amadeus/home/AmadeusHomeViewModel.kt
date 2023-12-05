package com.macaosoftware.sdui.app.marketplace.amadeus.home

import FormParam
import QueryParam
import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.data.ApiCredentials
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.CallResult
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.pablichj.incubator.amadeus.endpoint.accesstoken.AccessTokenDaoDelight
import com.pablichj.incubator.amadeus.endpoint.accesstoken.GetAccessTokenRequest
import com.pablichj.incubator.amadeus.endpoint.accesstoken.GetAccessTokenUseCase
import com.pablichj.incubator.amadeus.endpoint.accesstoken.ResolveAccessTokenUseCaseSource
import com.pablichj.incubator.amadeus.endpoint.accesstoken.model.AccessToken
import com.pablichj.incubator.amadeus.endpoint.hotels.HotelsByCityUseCase
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelsByCityResponseBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AmadeusHomeViewModel(
    database: Database,
    timeProvider: ITimeProvider,
    private val onHotelClick: (HotelListing) -> Unit
) : ComponentViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val _hotelByCityCode = MutableStateFlow<HotelState>(HotelState.Loading)
    val hotelByCityCode: StateFlow<HotelState> = _hotelByCityCode

    // We don't want to make a request everytime the App is brought to the foreground
    private var hotelsAlreadyFetch = false

    private val accessTokenDao = AccessTokenDaoDelight(
        database,
        timeProvider
    )

    var backPressListener: () -> Boolean = { false }

    override fun onAttach() {
        println("AmadeusHomeViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("AmadeusHomeViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("AmadeusHomeViewModel -  onStart() : ")

        if (hotelsAlreadyFetch) {
            return
        }

        checkSavedAccessTokenAndSearchHotelsByCity()
    }

    override fun onStop() {
        println("AmadeusHomeViewModel -  onStop() : ")
    }

    override fun handleBackPressed(): Boolean {
        return backPressListener()
    }

    private fun checkSavedAccessTokenAndSearchHotelsByCity() {
        viewModelScope.launch {

            val accessToken = ResolveAccessTokenUseCaseSource(
                Dispatchers,
                accessTokenDao
            ).doWork()

            if (accessToken != null) {
                getHotelByCityCode(accessToken,"MIA")
                return@launch
            }

            requestNewTokenAndFetchHotelsByCity()
        }
    }

    private fun requestNewTokenAndFetchHotelsByCity() {
        viewModelScope.launch {

            val callResult = GetAccessTokenUseCase(
                Dispatchers,
                GetAccessTokenRequest(
                    listOf(
                        FormParam.ClientId(ApiCredentials.apiKey),
                        FormParam.ClientSecret(ApiCredentials.apiSecret),
                        FormParam.GrantType(GetAccessTokenUseCase.AccessTokenGrantType),
                    )
                )
            ).doWork()

            when (callResult) {
                is CallResult.Error -> {
                    _hotelByCityCode.value = HotelState.Error(
                        "Access token error: ${callResult.error}"
                    )
                }

                is CallResult.Success -> {
                    val accessToken = callResult.responseBody
                    accessTokenDao.insert(accessToken)
                    getHotelByCityCode(accessToken,"MIA")
                }
            }
        }
    }

    private fun getHotelByCityCode(
        accessToken: AccessToken,
        cityCode: String) {
        viewModelScope.launch {

            _hotelByCityCode.value = HotelState.Loading

            when (val callResult = getHotelByCity(accessToken, cityCode)) {
                is CallResult.Error -> {
                    println("Error in hotels by city: ${callResult.error}")
                    _hotelByCityCode.value = HotelState.Error(
                        "Error in hotels by city: ${callResult.error}"
                    )
                    if(callResult.error.error == "401") {
                        requestNewTokenAndFetchHotelsByCity()
                    }
                }

                is CallResult.Success -> {
                    hotelsAlreadyFetch = true
                    _hotelByCityCode.value = HotelState.Success(
                        callResult.responseBody.data
                    )
                }
            }
        }
    }

    private suspend fun getHotelByCity(
        accessToken: AccessToken,
        iataCityCode: String
    ): CallResult<HotelsByCityResponseBody> {
        return HotelsByCityUseCase(
            Dispatchers,
            accessToken,
            QueryParam.CityCode(iataCityCode),
            QueryParam.Radius("2"),
            QueryParam.RadiusUnit("KM"),
            QueryParam.HotelSource("ALL")
        ).doWork()
    }

    fun hotelCardClick(hotelListing: HotelListing) = onHotelClick(hotelListing)

}
