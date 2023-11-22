package com.macaosoftware.sdui.app.marketplace.amadeus.search

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent

class SearchViewModelFactory : ComponentViewModelFactory<SearchViewModel> {
    override fun create(component: StateComponent<SearchViewModel>): SearchViewModel {
        return SearchViewModel(component)
    }
}