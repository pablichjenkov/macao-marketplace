package com.macaosoftware.sdui.app.viewmodel.factory

import com.macaosoftware.component.navbar.BottomNavigationComponent
import com.macaosoftware.component.navbar.BottomNavigationComponentViewModelFactory
import com.macaosoftware.component.navbar.BottomNavigationStatePresenterDefault
import com.macaosoftware.sdui.app.sdui.BottomNavigationSduiHandler
import com.macaosoftware.sdui.app.viewmodel.BottomNavigationViewModel

class BottomNavigationViewModelFactory(
    private val sduiHandler: BottomNavigationSduiHandler,
    private val bottomNavigationStatePresenter: BottomNavigationStatePresenterDefault
) : BottomNavigationComponentViewModelFactory<BottomNavigationViewModel> {

    override fun create(
        bottomNavigationComponent: BottomNavigationComponent<BottomNavigationViewModel>
    ): BottomNavigationViewModel {
        return BottomNavigationViewModel(
            sduiHandler = sduiHandler,
            bottomNavigationComponent = bottomNavigationComponent,
            bottomNavigationStatePresenter = bottomNavigationStatePresenter
        )
    }
}
