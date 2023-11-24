package com.macaosoftware.sdui.app.marketplace.amadeus.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Hotel
import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.clearBackStack
import com.macaosoftware.component.core.pop
import com.macaosoftware.component.core.push
import com.macaosoftware.component.topbar.TopBarComponent
import com.macaosoftware.component.topbar.TopBarComponentViewModel
import com.macaosoftware.component.topbar.TopBarItem
import com.macaosoftware.component.topbar.TopBarStatePresenter
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.amadeus.detail.HotelDetailsView
import com.macaosoftware.sdui.app.marketplace.amadeus.detail.HotelDetailsViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.detail.HotelDetailsViewModelFactory
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing

class AmadeusHomeCoordinatorViewModel(
    topBarComponent: TopBarComponent<AmadeusHomeCoordinatorViewModel>,
    override val topBarStatePresenter: TopBarStatePresenter,
    private val database: Database,
    private val timeProvider: ITimeProvider
) : TopBarComponentViewModel(topBarComponent) {

    private var homeComponent: Component = createNewAmadeusHomeComponent()
    private var detailComponent: Component? = null
    private var currentComponent: Component? = null

    override fun onAttach() {
        println("AmadeusHomeCoordinatorViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("AmadeusHomeCoordinatorViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("AmadeusHomeCoordinatorViewModel -  onStart() : ")

        println("${topBarComponent.instanceId()}::AmadeusHomeCoordinatorViewModel::onStart()")
        val shouldPushFirstChild: Boolean = if (currentComponent == null) {
            true
        } else if (topBarComponent.isFirstComponentInStackPreviousCache) {
            currentComponent != homeComponent
        } else false

        if (shouldPushFirstChild) {
            currentComponent = homeComponent
            topBarComponent.navigator.clearBackStack()
            topBarComponent.navigator.push(homeComponent)
        }
    }

    override fun onStop() {
        println("AmadeusHomeCoordinatorViewModel -  onStop() : ")
    }

    override fun mapComponentToStackBarItem(topComponent: Component): TopBarItem {
        return when {
            topComponent == homeComponent -> {
                TopBarItem(
                    label = "Home",
                    icon = Icons.Filled.Home
                )
            }

            topComponent == detailComponent -> {
                TopBarItem(
                    label = "Detail",
                    icon = Icons.Filled.Hotel
                )
            }

            else -> {
                TopBarItem(
                    label = "Unknown Screen",
                    icon = Icons.Filled.Close
                )
            }
        }
    }

    override fun onCheckChildForNextUriFragment(nextUriFragment: String): Component? {
        return null
    }

    private fun createNewAmadeusHomeComponent(): StateComponent<AmadeusHomeViewModel> {
        return StateComponent<AmadeusHomeViewModel>(
            viewModelFactory = AmadeusHomeViewModelFactory(
                database,
                timeProvider,
                onHotelClick = { hotelListing ->
                    createNewHotelDetailChildComponent(
                        hotelListing = hotelListing,
                        imageUrl = Util.IMAGE,
                        onBackPressed = {
                            topBarComponent.navigator.pop()
                        }
                    ).also {
                        detailComponent = it
                        topBarComponent.navigator.push(it)
                    }
                }
            ),
            content = AmadeusHomeView
        )
    }

    private fun createNewHotelDetailChildComponent(
        hotelListing: HotelListing,
        imageUrl: String,
        onBackPressed: () -> Unit
    ): StateComponent<HotelDetailsViewModel> {
        return StateComponent<HotelDetailsViewModel>(
            viewModelFactory = HotelDetailsViewModelFactory(
                database,
                hotelListing,
                imageUrl,
                onBackPressed
            ),
            content = HotelDetailsView
        )
    }
}
