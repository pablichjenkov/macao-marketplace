package com.macaosoftware.sdui.app.marketplace.amadeus.home

import com.macaosoftware.component.topbar.TopBarComponent
import com.macaosoftware.component.topbar.TopBarComponentViewModelFactory
import com.macaosoftware.component.topbar.TopBarStatePresenter
import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing

class AmadeusHomeCoordinatorViewModelFactory(
    private val topBarStatePresenter: TopBarStatePresenter,
    private val database: Database,
    private val timeProvider: ITimeProvider
) : TopBarComponentViewModelFactory<AmadeusHomeCoordinatorViewModel> {

    override fun create(
        topBarComponent: TopBarComponent<AmadeusHomeCoordinatorViewModel>
    ): AmadeusHomeCoordinatorViewModel {
        return AmadeusHomeCoordinatorViewModel(
            topBarComponent,
            topBarStatePresenter,
            database,
            timeProvider
        )
    }
}
