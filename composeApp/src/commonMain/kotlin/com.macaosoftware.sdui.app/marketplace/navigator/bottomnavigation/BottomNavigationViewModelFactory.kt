package com.macaosoftware.sdui.app.marketplace.navigator.bottomnavigation

import com.macaosoftware.component.bottomnavigation.BottomNavigationComponent
import com.macaosoftware.component.bottomnavigation.BottomNavigationComponentViewModelFactory
import com.macaosoftware.component.bottomnavigation.BottomNavigationStatePresenterDefault
import org.koin.core.component.KoinComponent

class BottomNavigationViewModelFactory(
    private val koinComponent: KoinComponent,
    private val sduiHandler: BottomNavigationSduiHandler,
    private val bottomNavigationStatePresenter: BottomNavigationStatePresenterDefault
) : BottomNavigationComponentViewModelFactory<BottomNavigationViewModel> {

    override fun create(
        bottomNavigationComponent: BottomNavigationComponent<BottomNavigationViewModel>
    ): BottomNavigationViewModel {
        return BottomNavigationViewModel(
            koinComponent = koinComponent,
            sduiHandler = sduiHandler,
            bottomNavigationComponent = bottomNavigationComponent,
            bottomNavigationStatePresenter = bottomNavigationStatePresenter
        )
    }
}
