package com.macaosoftware.sdui.app.marketplace.navigator.drawer

import com.macaosoftware.component.drawer.DrawerComponent
import com.macaosoftware.component.drawer.DrawerComponentViewModelFactory
import com.macaosoftware.component.drawer.DrawerStatePresenterDefault
import com.macaosoftware.sdui.app.marketplace.navigator.drawer.DrawerSduiHandler
import com.macaosoftware.sdui.app.marketplace.navigator.drawer.DrawerViewModel
import org.koin.core.component.KoinComponent

class DrawerViewModelFactory(
    private val koinComponent: KoinComponent,
    private val sduiHandler: DrawerSduiHandler,
    private val drawerStatePresenter: DrawerStatePresenterDefault
) : DrawerComponentViewModelFactory<DrawerViewModel> {

    override fun create(
        drawerComponent: DrawerComponent<DrawerViewModel>
    ): DrawerViewModel {
        return DrawerViewModel(
            koinComponent = koinComponent,
            sduiHandler = sduiHandler,
            drawerComponent = drawerComponent,
            drawerStatePresenter = drawerStatePresenter
        )
    }
}
