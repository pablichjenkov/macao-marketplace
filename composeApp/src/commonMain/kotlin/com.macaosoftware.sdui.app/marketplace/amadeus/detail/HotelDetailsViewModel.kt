package com.macaosoftware.sdui.app.marketplace.amadeus.detail

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class HotelDetailsViewModel(
    database: Database,
    val hotelListing: HotelListing,
    val imageUrl: String,
    private val onBackPressed: () -> Unit
) : ComponentViewModel() {

    override fun onAttach() {
        println("HotelDetailsViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("HotelDetailsViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("HotelDetailsViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("HotelDetailsViewModel -  onStop() : ")
    }

    fun onBackClicked() = onBackPressed()

}
