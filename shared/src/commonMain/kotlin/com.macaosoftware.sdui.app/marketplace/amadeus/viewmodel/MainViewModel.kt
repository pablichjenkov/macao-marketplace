package com.macaosoftware.sdui.app.marketplace.amadeus.viewmodel

import com.macaosoftware.sdui.app.marketplace.amadeus.repository.Repository
import com.macaosoftware.sdui.app.marketplace.amadeus.util.states.HotelState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    private val _hotelByCityCode = MutableStateFlow<HotelState>(HotelState.Loading)
    val hotelByCityCode : StateFlow<HotelState> = _hotelByCityCode

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


}