package com.macaosoftware.sdui.app.marketplace.amadeus.ui.viewmodel

import com.macaosoftware.sdui.app.marketplace.amadeus.data.repository.Repository
import com.macaosoftware.sdui.app.marketplace.amadeus.domain.usecases.HotelOfferState
import com.macaosoftware.sdui.app.marketplace.amadeus.domain.usecases.HotelState
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.offers.HotelOffersState
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.travel.TravelState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    //Hotel By City Code
    private val _hotelByCityCode = MutableStateFlow<HotelState>(HotelState.Loading)
    val hotelByCityCode : StateFlow<HotelState> = _hotelByCityCode

    //Hotel Offer
    private val _hotelOffers = MutableStateFlow<HotelOfferState>(HotelOfferState.Loading)
    val hotelOffers : StateFlow<HotelOfferState> = _hotelOffers

    //Hotels
    private val _hotels = MutableStateFlow<HotelOffersState>(HotelOffersState.Loading)
    val hotels : StateFlow<HotelOffersState> = _hotels

    //Travel
    private val _travel = MutableStateFlow<TravelState>(TravelState.Loading)
    val travel : StateFlow<TravelState> = _travel

    //Hotel by City Code
    fun getHotelByCityCode(cityCode: String){
        viewModelScope.launch {
            _hotelByCityCode.value = HotelState.Loading
            try {
                val response = repository.getHotelByCity(cityCode)
                _hotelByCityCode.value = HotelState.Success(response)
            }catch (e: Exception){
                _hotelByCityCode.value = HotelState.Error(e.message.toString())
            }
        }
    }

    //Hotel Offers
    fun getHotelOffers(hotelId: String){
        viewModelScope.launch {
            _hotelOffers.value = HotelOfferState.Loading
            try {
                val response = repository.getHotelOffer(hotelId)
                _hotelOffers.value = HotelOfferState.Success(response)
            }catch (e: Exception){
                _hotelOffers.value = HotelOfferState.Error(e.message.toString())
            }
        }
    }

    //Hotels
    fun getHotels(){
        viewModelScope.launch {
            _hotels.value = HotelOffersState.Loading
            try {
                val response = repository.getHotels()
                _hotels.value = HotelOffersState.Success(response)
            }catch (e: Exception){
                _hotels.value = HotelOffersState.Error(e.message.toString())
            }
        }
    }

    //Travel
    fun getTravel(){
        viewModelScope.launch {
            _travel.value = TravelState.Loading
            try {
                val response = repository.getTravel()
                _travel.value = TravelState.Success(response)
            }catch (e: Exception){
                _travel.value = TravelState.Error(e.message.toString())
            }
        }
    }


}