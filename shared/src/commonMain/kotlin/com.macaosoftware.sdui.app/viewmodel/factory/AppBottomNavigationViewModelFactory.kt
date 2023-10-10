package com.macaosoftware.sdui.app.viewmodel.factory

import com.macaosoftware.component.navbar.BottomNavigationComponent
import com.macaosoftware.component.navbar.BottomNavigationComponentViewModelFactory
import com.macaosoftware.component.navbar.BottomNavigationStatePresenterDefault
import com.macaosoftware.sdui.app.AppBottomSduiHandler
import com.macaosoftware.sdui.app.viewmodel.AppBottomNavigationViewModel

class AppBottomNavigationViewModelFactory(
    private val sduiHandler: AppBottomSduiHandler,
    private val bottomNavigationStatePresenter: BottomNavigationStatePresenterDefault
) : BottomNavigationComponentViewModelFactory<AppBottomNavigationViewModel> {

    override fun create(
        bottomNavigationComponent: BottomNavigationComponent<AppBottomNavigationViewModel>
    ): AppBottomNavigationViewModel {
        return AppBottomNavigationViewModel(
            sduiHandler = sduiHandler,
            bottomNavigationComponent = bottomNavigationComponent,
            bottomNavigationStatePresenter = bottomNavigationStatePresenter
        )
    }
}
