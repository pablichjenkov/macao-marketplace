package com.macaosoftware.sdui.app.marketplace.amadeus.detail

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing

class HotelDetailsViewModelFactory(
    private val database: Database,
    private val hotelListing: HotelListing,
    private val imageUrl: String,
    private val onBackPressed: () -> Unit
) : ComponentViewModelFactory<HotelDetailsViewModel> {

    override fun create(component: StateComponent<HotelDetailsViewModel>): HotelDetailsViewModel {
        return HotelDetailsViewModel(database, hotelListing, imageUrl, onBackPressed)
    }
}
