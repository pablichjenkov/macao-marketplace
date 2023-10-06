package com.pablichj.incubator.amadeus.demo.viewmodel.factory

import com.macaosoftware.component.navbar.BottomNavigationComponent
import com.macaosoftware.component.navbar.BottomNavigationComponentViewModelFactory
import com.macaosoftware.component.navbar.BottomNavigationStatePresenterDefault
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.demo.viewmodel.AppBottomNavigationViewModel

class AppBottomNavigationViewModelFactory(
    private val database: Database,
    private val bottomNavigationStatePresenter: BottomNavigationStatePresenterDefault
) : BottomNavigationComponentViewModelFactory<AppBottomNavigationViewModel> {

    override fun create(
        bottomNavigationComponent: BottomNavigationComponent<AppBottomNavigationViewModel>
    ): AppBottomNavigationViewModel {
        return AppBottomNavigationViewModel(
            database = database,
            bottomNavigationComponent = bottomNavigationComponent,
            bottomNavigationStatePresenter = bottomNavigationStatePresenter
        )
    }
}
