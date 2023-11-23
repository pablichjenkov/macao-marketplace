package com.macaosoftware.sdui.app.marketplace.amadeus.profile

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

class ProfileViewModel(

    private val homeComponent: StateComponent<ProfileViewModel>
): ComponentViewModel() {
    override fun onAttach() {
        println("ProfileViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("ProfileViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("ProfileViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("ProfileViewModel -  onStop() : ")
    }
}
