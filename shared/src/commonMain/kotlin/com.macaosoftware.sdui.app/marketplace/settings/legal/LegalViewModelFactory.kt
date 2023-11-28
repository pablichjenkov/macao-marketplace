package com.macaosoftware.sdui.app.marketplace.settings.legal

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class LegalViewModelFactory(

) : ComponentViewModelFactory<LegalViewModel> {
    override fun create(component: StateComponent<LegalViewModel>): LegalViewModel {
        return LegalViewModel(component)
    }
}
