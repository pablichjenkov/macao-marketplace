package com.macaosoftware.sdui.app.viewmodel.amadeus.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent
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

class AHomeScreenViewModel(
  private val homeComponent: StateComponent<AHomeScreenViewModel>
): ComponentViewModel() {
    override fun onAttach() {
        println("AHomeScreenViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("AHomeScreenViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("AHomeScreenViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("AHomeScreenViewModel -  onStop() : ")
    }

}
