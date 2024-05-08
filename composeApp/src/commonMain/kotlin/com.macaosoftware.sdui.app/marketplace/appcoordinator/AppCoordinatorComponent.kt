package com.macaosoftware.sdui.app.marketplace.appcoordinator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.deeplink.DeepLinkResult
import com.macaosoftware.sdui.app.domain.SduiComponentFactory

class AppCoordinatorComponent(
    sduiComponentFactory: SduiComponentFactory
) : Component() {

    //private var activeComponent: Component =
    //     sduiComponentFactory.getComponentById("AuthorizationComponent")

    override fun onAttach() {
        super.onAttach()
    }

    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDeepLinkNavigateTo(matchingComponent: Component): DeepLinkResult {
        return super.onDeepLinkNavigateTo(matchingComponent)
    }

    @Composable
    override fun Content(modifier: Modifier) {
    }
}
