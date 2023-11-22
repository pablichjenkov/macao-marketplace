package com.macaosoftware.sdui.app.marketplace.amadeus.ui.viewmodel

import com.macaosoftware.sdui.app.marketplace.amadeus.data.repository.Repository
import com.macaosoftware.sdui.app.marketplace.amadeus.domain.usecases.HotelState
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