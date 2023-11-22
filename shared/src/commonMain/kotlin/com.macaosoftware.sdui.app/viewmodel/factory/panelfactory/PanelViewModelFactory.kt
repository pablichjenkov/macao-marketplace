package com.macaosoftware.sdui.app.viewmodel.factory.panelfactory

import com.macaosoftware.component.panel.PanelComponent
import com.macaosoftware.component.panel.PanelComponentViewModelFactory
import com.macaosoftware.component.panel.PanelStatePresenterDefault
import com.macaosoftware.sdui.app.sdui.PanelSduiHandler
import com.macaosoftware.sdui.app.viewmodel.panelViewModel.PanelViewModel

class PanelViewModelFactory(
    private val sduiHandler: PanelSduiHandler,
    private val panelStatePresenter: PanelStatePresenterDefault
) : PanelComponentViewModelFactory<PanelViewModel> {

    override fun create(
        panelComponent: PanelComponent<PanelViewModel>
    ): PanelViewModel {
        return PanelViewModel(
            sduiHandler = sduiHandler,
            panelComponent = panelComponent,
            panelStatePresenter = panelStatePresenter
        )
    }
}
