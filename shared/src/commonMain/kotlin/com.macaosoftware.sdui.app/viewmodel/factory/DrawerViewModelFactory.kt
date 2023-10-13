package com.macaosoftware.sdui.app.viewmodel.factory

import com.macaosoftware.component.drawer.DrawerComponent
import com.macaosoftware.component.drawer.DrawerComponentViewModelFactory
import com.macaosoftware.component.drawer.DrawerStatePresenterDefault
import com.macaosoftware.sdui.app.sdui.DrawerSduiHandler
import com.macaosoftware.sdui.app.viewmodel.DrawerViewModel

class DrawerViewModelFactory(
    private val sduiHandler: DrawerSduiHandler,
    private val drawerStatePresenter: DrawerStatePresenterDefault
) : DrawerComponentViewModelFactory<DrawerViewModel> {

    override fun create(
        drawerComponent: DrawerComponent<DrawerViewModel>
    ): DrawerViewModel {
        return DrawerViewModel(
            sduiHandler = sduiHandler,
            drawerComponent = drawerComponent,
            drawerStatePresenter = drawerStatePresenter
        )
    }
}
