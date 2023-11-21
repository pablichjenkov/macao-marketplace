package com.macaosoftware.sdui.app.viewmodel.factory

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.viewmodel.SearchViewModel

class SearchViewModelFactory : ComponentViewModelFactory<SearchViewModel> {
    override fun create(component: StateComponent<SearchViewModel>): SearchViewModel {
        return SearchViewModel(component)
    }
}