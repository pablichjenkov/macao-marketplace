package com.macaosoftware.sdui.app.marketplace.amadeus.airport

import FormParam
import QueryParam
import androidx.compose.runtime.mutableStateOf
import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.CallResult
import com.pablichj.incubator.amadeus.common.DefaultTimeProvider
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.macaosoftware.sdui.app.marketplace.amadeus.data.ApiCredentials
import com.macaosoftware.sdui.app.marketplace.amadeus.data.TestData
import com.pablichj.incubator.amadeus.endpoint.accesstoken.AccessTokenDaoDelight
import com.pablichj.incubator.amadeus.endpoint.accesstoken.GetAccessTokenRequest
import com.pablichj.incubator.amadeus.endpoint.accesstoken.GetAccessTokenUseCase
import com.pablichj.incubator.amadeus.endpoint.accesstoken.ResolveAccessTokenUseCaseSource
import com.pablichj.incubator.amadeus.endpoint.airport.AirportAndCitySearchUseCase
import com.pablichj.incubator.amadeus.endpoint.offers.flight.FlightOffersConfirmationRequest
import com.pablichj.incubator.amadeus.endpoint.offers.flight.FlightOffersConfirmationResponse
import com.pablichj.incubator.amadeus.endpoint.offers.flight.FlightOffersConfirmationUseCase
import com.pablichj.incubator.amadeus.endpoint.offers.flight.FlightOffersRequest
import com.pablichj.incubator.amadeus.endpoint.offers.flight.FlightOffersResponse
import com.pablichj.incubator.amadeus.endpoint.offers.flight.FlightOffersUseCase
import com.pablichj.incubator.amadeus.endpoint.offers.flight.model.FlightOffer
import com.pablichj.incubator.amadeus.endpoint.offers.flight.model.FlightOffersConfirmationRequestBody
import com.pablichj.incubator.amadeus.endpoint.offers.flight.model.FlightOffersConfirmationRequestBodyBoxing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AirportDemoViewModel(
    private val database: Database
) : ComponentViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val timeProvider: ITimeProvider = DefaultTimeProvider()
    private val accessTokenDao = AccessTokenDaoDelight(
        database,
        timeProvider
    )

    val console = mutableStateOf("")
    private var flightOffers: List<FlightOffer>? = null

    override fun onAttach() {
        println("AirportDemoComponent::onAttach()")
        output("AirportDemoComponent::onAttach()")
    }

    override fun onStart() {
        println("AirportDemoComponent::onStart()")
        output("AirportDemoComponent::onStart()")
    }

    override fun onStop() {
        println("AirportDemoComponent::onStop()")
        output("AirportDemoComponent::onStop()")
    }

    override fun onDetach() {
        println("AirportDemoComponent::onDetach()")
        output("AirportDemoComponent::onDetach()")
    }

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

    fun searchAirportByKeyword() {
        coroutineScope.launch {
            val accessToken = ResolveAccessTokenUseCaseSource(
                Dispatchers, accessTokenDao
            ).doWork()

            if (accessToken == null) {
                output("No saved token")
                return@launch
            } else {
                output("Using saved token: ${accessToken.accessToken}")
            }

            // Example query url
            // ?subType=CITY
            // &keyword=MUC
            // &page%5Blimit%5D=10
            // &page%5Boffset%5D=0
            // &sort=analytics.travelers.score
            // &view=FULL
            val callResult = AirportAndCitySearchUseCase(
                Dispatchers,
                accessToken,
                QueryParam.Keyword("New Orleans"),
                QueryParam.SubType("AIRPORT")
            ).doWork()

            when (callResult) {
                is CallResult.Error -> {
                    output("Error fetching Airports: ${callResult.error}")
                }

                is CallResult.Success -> {
                    callResult.responseBody.data.forEach {
                        output(
                            """
                            Airport Name: ${it.name}
                            Airport Detailed Name: ${it.detailedName}
                            Airport Id: ${it.id}
                            Location Subtype: ${it.subType}
                            Airport Country: ${it.address.countryName}
                            Airport State: ${it.address.stateCode}
                            Airport City: ${it.address.cityName}
                        """.trimIndent()
                        )
                    }
                }
            }

        }
    }

    fun searchFlightOffersGet() {
        coroutineScope.launch {
            val accessToken = ResolveAccessTokenUseCaseSource(
                Dispatchers, accessTokenDao
            ).doWork()

            if (accessToken == null) {
                output("No saved token")
                return@launch
            } else {
                output("Using saved token: ${accessToken.accessToken}")
            }

            val callResult = FlightOffersUseCase(
                Dispatchers, FlightOffersRequest(accessToken, TestData.flightOffersRequestBody)
            ).doWork()

            when (callResult) {
                is CallResult.Error -> {
                    output("Error fetching flight offers: ${callResult.error}")
                }

                is CallResult.Success<FlightOffersResponse> -> {
                    flightOffers = callResult.responseBody.data
                    callResult.responseBody.data.forEach {
                        output(
                            """
                            Offer Id: ${it.id}
                            Offer type: ${it.type}
                            Offer itineraries: ${it.itineraries}
                            Offer lastTicketingDate: ${it.lastTicketingDate}
                            Offer instantTicketingRequired: ${it.instantTicketingRequired}
                            Offer is oneWay: ${it.oneWay}
                            Offer source: ${it.source}
                            Offer nonHomogeneous: ${it.nonHomogeneous}
                            Offer nonHomogeneous: ${it.nonHomogeneous}
                        """.trimIndent()
                        )
                        output("Itineraries:")
                        it.itineraries.forEach {
                            output(
                                """ 
                                Itinerary duration: ${it.duration}
                                Itinerary segments: ${it.segments}
                            """.trimIndent()
                            )
                        }
                        output("Pricing:")
                        output("Price currency: ${it.price.currency}")
                        output("Price base: ${it.price.base}")
                        output("Price total: ${it.price.total}")
                        output("Price grandTotal: ${it.price.grandTotal}")
                        output("Price Fees:")
                        it.price.fees.forEach {
                            output(
                                """ 
                                Fee amount: ${it.amount}
                                Fee type: ${it.type}
                            """.trimIndent()
                            )
                        }
                    }
                }
            }

        }
    }

    fun confirmFlightOffersGet() {
        coroutineScope.launch {

            val offerToVerify = flightOffers?.firstOrNull() ?: run {
                output("No Offer selected to confirm")
                return@launch
            }

            val accessToken = ResolveAccessTokenUseCaseSource(
                Dispatchers, accessTokenDao
            ).doWork()

            if (accessToken == null) {
                output("No saved token")
                return@launch
            } else {
                output("Using saved token: ${accessToken.accessToken}")
            }

            val callResult = FlightOffersConfirmationUseCase(
                Dispatchers,
                FlightOffersConfirmationRequest(
                    accessToken,
                    FlightOffersConfirmationRequestBodyBoxing(
                        data = FlightOffersConfirmationRequestBody(
                            type = "flight-offers-pricing",
                            flightOffers = listOf(offerToVerify)
                        )
                    )
                )
            ).doWork()

            when (callResult) {
                is CallResult.Error -> {
                    output("Error fetching flight offers: ${callResult.error}")
                }

                is CallResult.Success<FlightOffersConfirmationResponse> -> {
                    callResult.responseBody.data.forEach {
                        output(
                            """
                            Offer Id: ${it.id}
                            Offer type: ${it.type}
                            Offer itineraries: ${it.itineraries}
                            Offer lastTicketingDate: ${it.lastTicketingDate}
                            Offer instantTicketingRequired: ${it.instantTicketingRequired}
                            Offer is oneWay: ${it.oneWay}
                            Offer source: ${it.source}
                            Offer nonHomogeneous: ${it.nonHomogeneous}
                            Offer nonHomogeneous: ${it.nonHomogeneous}
                        """.trimIndent()
                        )
                        output("Itineraries:")
                        it.itineraries.forEach {
                            output(
                                """ 
                                Itinerary duration: ${it.duration}
                                Itinerary segments: ${it.segments}
                            """.trimIndent()
                            )
                        }
                        output("Pricing:")
                        output("Price currency: ${it.price.currency}")
                        output("Price base: ${it.price.base}")
                        output("Price total: ${it.price.total}")
                        output("Price grandTotal: ${it.price.grandTotal}")
                        output("Price Fees:")
                        it.price.fees.forEach {
                            output(
                                """ 
                                Fee amount: ${it.amount}
                                Fee type: ${it.type}
                            """.trimIndent()
                            )
                        }
                    }
                }
            }

        }
    }

    private fun output(text: String) {
        console.value += "\n$text"
    }
}
