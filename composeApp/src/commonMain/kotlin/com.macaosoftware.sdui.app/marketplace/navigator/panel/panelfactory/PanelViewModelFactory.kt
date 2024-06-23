package com.macaosoftware.sdui.app.marketplace.navigator.panel.panelfactory

import com.macaosoftware.component.panel.PanelComponent
import com.macaosoftware.component.panel.PanelComponentViewModelFactory
import com.macaosoftware.component.panel.PanelStatePresenterDefault
import com.macaosoftware.sdui.app.marketplace.navigator.panel.PanelSduiHandler
import com.macaosoftware.sdui.app.marketplace.navigator.panel.panelViewModel.PanelViewModel
import org.koin.core.component.KoinComponent

class PanelViewModelFactory(
    private val koinComponent: KoinComponent,
    private val sduiHandler: PanelSduiHandler,
    private val panelStatePresenter: PanelStatePresenterDefault
) : PanelComponentViewModelFactory<PanelViewModel> {

    override fun create(
        panelComponent: PanelComponent<PanelViewModel>
    ): PanelViewModel {
        return PanelViewModel(
            koinComponent = koinComponent,
            sduiHandler = sduiHandler,
            panelComponent = panelComponent,
            panelStatePresenter = panelStatePresenter
        )
    }
}
