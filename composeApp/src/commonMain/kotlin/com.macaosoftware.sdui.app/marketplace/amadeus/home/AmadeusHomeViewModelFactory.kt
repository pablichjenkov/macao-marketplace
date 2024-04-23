package com.macaosoftware.sdui.app.marketplace.amadeus.home

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing

class AmadeusHomeViewModelFactory(
    private val database: Database,
    private val timeProvider: ITimeProvider,
    private val onHotelClick: (HotelListing) -> Unit
) : ComponentViewModelFactory<AmadeusHomeViewModel> {

    override fun create(component: StateComponent<AmadeusHomeViewModel>): AmadeusHomeViewModel {
        return AmadeusHomeViewModel(database, timeProvider, onHotelClick)
    }
}
