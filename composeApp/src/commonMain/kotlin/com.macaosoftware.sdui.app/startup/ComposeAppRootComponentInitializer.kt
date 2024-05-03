package com.macaosoftware.sdui.app.startup

import com.macaosoftware.app.RootComponentInitializer
import com.macaosoftware.component.core.Component
import com.macaosoftware.sdui.app.data.SduiRemoteService
import com.macaosoftware.sdui.app.domain.SduiComponentFactory
import com.macaosoftware.util.MacaoResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class ComposeAppRootComponentInitializer : RootComponentInitializer {

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(koinComponent: KoinComponent): MacaoResult<Component> {

        val sduiRemoteService = koinComponent.get<SduiRemoteService>()

        val sduiComponentFactory = SduiComponentFactory(koinComponent)
        val rootComponentJsonResilience = sduiRemoteService.getRootJsonResilience()
        val rootComponentJson = sduiRemoteService.getRemoteRootComponent("123")

        val rootComponent = sduiComponentFactory.getComponentInstanceOf(
            componentJson = rootComponentJson ?: rootComponentJsonResilience
        )

        return MacaoResult.Success(rootComponent)
    }
}
