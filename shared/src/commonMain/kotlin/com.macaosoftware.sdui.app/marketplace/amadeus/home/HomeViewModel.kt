package com.macaosoftware.sdui.app.marketplace.amadeus.home

import androidx.compose.runtime.mutableStateOf
import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.sdui.app.data.ApiCredentials
import com.macaosoftware.sdui.app.data.TestData
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.CallResult
import com.pablichj.incubator.amadeus.common.DefaultTimeProvider
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.pablichj.incubator.amadeus.endpoint.accesstoken.AccessTokenDaoDelight
import com.pablichj.incubator.amadeus.endpoint.accesstoken.GetAccessTokenRequest
import com.pablichj.incubator.amadeus.endpoint.accesstoken.GetAccessTokenUseCase
import com.pablichj.incubator.amadeus.endpoint.accesstoken.ResolveAccessTokenUseCaseSource
import com.pablichj.incubator.amadeus.endpoint.booking.hotel.HotelBookingRequest
import com.pablichj.incubator.amadeus.endpoint.booking.hotel.HotelBookingUseCase
import com.pablichj.incubator.amadeus.endpoint.city.CitySearchUseCase
import com.pablichj.incubator.amadeus.endpoint.hotels.HotelsByCityUseCase
import com.pablichj.incubator.amadeus.endpoint.offers.hotel.GetOfferRequest
import com.pablichj.incubator.amadeus.endpoint.offers.hotel.GetOfferUseCase
import com.pablichj.incubator.amadeus.endpoint.offers.hotel.MultiHotelOffersUseCase
import com.pablichj.incubator.amadeus.endpoint.offers.hotel.model.HotelOfferSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val database: Database,
   // private val homeComponent: StateComponent<HomeViewModel>
): ComponentViewModel() {
    override fun onAttach() {
        println("HomeViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("HomeViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("HomeViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("HomeViewModel -  onStop() : ")
    }

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val timeProvider: ITimeProvider = DefaultTimeProvider()
    private val accessTokenDao = AccessTokenDaoDelight(
        database,
        timeProvider
    )

    val console = mutableStateOf("")
    var hotelOffers: List<HotelOfferSearch>? = null

    fun getAccessToken() {
        coroutineScope.launch {
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
                    output("Error fetching access token: ${callResult.error}")
                }

                is CallResult.Success -> {
                    val accessToken = callResult.responseBody
                    accessTokenDao.insert(accessToken)
                    output("SQDelight Insert Token Success: $accessToken")
                }
            }
        }
    }

    fun getCitiesByKeyword() {
        coroutineScope.launch {
            val accessToken = ResolveAccessTokenUseCaseSource(
                Dispatchers,
                accessTokenDao
            ).doWork()

            if (accessToken == null) {
                output("No saved token")
                return@launch
            } else {
                output("Using saved token: ${accessToken.accessToken}")
            }

            // Example query url
            //?countryCode=FR
            // &keyword=PARIS
            // &max=10
            val callResult = CitySearchUseCase(
                Dispatchers,
                accessToken,
                QueryParam.CountryCode("US"),
                QueryParam.Keyword("Miami"),
                QueryParam.Max("5")
            ).doWork()

            when (callResult) {
                is CallResult.Error -> {
                    output("Error in city search: ${callResult.error}")
                }

                is CallResult.Success -> {
                    callResult.responseBody.data.forEach {
                        output(
                            """
                            City Name = ${it.name}
                            City Address = ${it.address}
                            City Type = ${it.type}
                            City Subtype = ${it.subType}
                        """.trimIndent()
                        )
                    }
                }
            }
        }
    }

    fun getHotelsByCity() {
        coroutineScope.launch {
            val accessToken = ResolveAccessTokenUseCaseSource(
                Dispatchers,
                accessTokenDao
            ).doWork()

            if (accessToken == null) {
                output("No saved token")
                return@launch
            } else {
                output("Using saved token: ${accessToken.accessToken}")
            }

            // Example query url
            //?cityCode=PAR
            // &radius=1
            // &radiusUnit=KM
            // &hotelSource=ALL
            val callResult = HotelsByCityUseCase(
                Dispatchers,
                accessToken,
                QueryParam.CityCode("PAR"),
                QueryParam.Radius("1"),
                QueryParam.RadiusUnit("KM"),
                QueryParam.HotelSource("ALL")
            ).doWork()

            when (callResult) {
                is CallResult.Error -> {
                    output("Error in hotels by city: ${callResult.error}")
                }

                is CallResult.Success -> {
                    callResult.responseBody.data.forEach {
                        output(
                            """
                                Hotel ID: ${it.hotelId}
                                Geocode: ${it.geoCode}
                                Dupe ID: ${it.dupeId}
                                -----
                            """.trimMargin()
                        )
                    }
                }
            }

        }
    }

    fun getMultiHotelsOffers() {
        coroutineScope.launch {
            val accessToken = ResolveAccessTokenUseCaseSource(
                Dispatchers,
                accessTokenDao
            ).doWork()

            if (accessToken == null) {
                output("No saved token")
                return@launch
            } else {
                output("Using saved token: ${accessToken.accessToken}")
            }

            // Example queries
            //?hotelIds=MCLONGHM&adults=1&checkInDate=2023-11-22&roomQuantity=1&paymentPolicy=NONE&bestRateOnly=true
            //hotelIds=HHMIA500&adults=1&checkInDate=2023-12-31&roomQuantity=1&bestRateOnly=false
            val callResult = MultiHotelOffersUseCase(
                Dispatchers,
                accessToken,
                QueryParam.HotelIds("HHMIA500"),
                QueryParam.Adults("1"),
                QueryParam.CheckInDate("2023-12-20"),
                QueryParam.RoomQuantity("1"),
                QueryParam.BestRateOnly("false")
            ).doWork()

            when (callResult) {
                is CallResult.Error -> {
                    output("Error in multi hotel offers: ${callResult.error}")
                }

                is CallResult.Success -> {
                    hotelOffers = callResult.responseBody.data
                    callResult.responseBody.data.forEach {
                        output(
                            """
                                Hotel ID: ${it.hotel.hotelId}
                                Available: ${it.available}
                                Type: ${it.type}
                            """.trimMargin()
                        )
                        output("Offers: ")
                        it.offers.forEach {
                            output(
                                """Offer ID: ${it.id}
                                   Base Price: ${it.price.base}
                                   Checkin: ${it.checkInDate}
                            """.trimMargin()
                            )
                        }
                    }
                }
            }
        }
    }

    fun getFullOfferDetails(offerId: String) {
        coroutineScope.launch {
            val accessToken = ResolveAccessTokenUseCaseSource(
                Dispatchers,
                accessTokenDao
            ).doWork()

            if (accessToken == null) {
                output("No saved token")
                return@launch
            } else {
                output("Using saved token: ${accessToken.accessToken}")
            }

            val callResult = GetOfferUseCase(
                Dispatchers,
                GetOfferRequest(accessToken, offerId)
            ).doWork()

            when (callResult) {
                is CallResult.Error -> {
                    output("Error in get offer by id: ${callResult.error}")
                }

                is CallResult.Success -> {
                    output("Offer in Hotel: ${callResult.responseBody.data.hotel.name}")
                    callResult.responseBody.data.offers.forEach {
                        output(
                            """
                            Offer Id: ${it.id}
                            CheckInDate: ${it.checkInDate}
                            CheckOutDate: ${it.checkOutDate}
                            Guests Adults: ${it.guests?.adults}
                            Guests Kids: ${it.guests?.childAges?.size}
                            Base Price: ${it.price.base}
                        """.trimIndent()
                        )
                    }
                }
            }
        }
    }

    fun hotelBook() {
        coroutineScope.launch {
            val accessToken = ResolveAccessTokenUseCaseSource(
                Dispatchers,
                accessTokenDao
            ).doWork()

            if (accessToken == null) {
                output("No saved token")
                return@launch
            } else {
                output("Using saved token: ${accessToken.accessToken}")
            }

            val callResult = HotelBookingUseCase(
                Dispatchers,
                HotelBookingRequest(accessToken, TestData.hotelBookingRequestBody)
            ).doWork()

            when (callResult) {
                is CallResult.Error -> {
                    output("Error in hotel booking: ${callResult.error}")
                }

                is CallResult.Success -> {
                    callResult.responseBody.data.forEach {
                        output(
                            """
                            Booking Confirmation Id: ${it.id}
                            Provider Confirmation Id: ${it.providerConfirmationId}
                            Booking Confirmation Type: ${it.type}
                            Booking Associated Records:
                        """.trimIndent()
                        )
                        it.associatedRecords.forEach {
                            output(
                                """
                                Record Reference: ${it.reference}
                                Record Origin System Code: ${it.originSystemCode}
                            """.trimIndent()
                            )
                        }
                    }
                }
            }
        }
    }

    fun output(text: String) {
        console.value += "\n$text"
    }
}
