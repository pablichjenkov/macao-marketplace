package com.macaosoftware.sdui.app.startup

import com.macaosoftware.app.RootComponentInitializer
import com.macaosoftware.component.core.Component
import com.macaosoftware.sdui.app.sdui.SduiComponentFactory
import com.macaosoftware.sdui.data.SduiRemoteService
import com.macaosoftware.util.MacaoResult
import org.koin.core.component.KoinComponent

class ComposeAppRootComponentInitializer : RootComponentInitializer {

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(koinComponent: KoinComponent): MacaoResult<Component> {

        val sduiComponentFactory = SduiComponentFactory(koinComponent)
        val rootComponentJsonResilience = SduiRemoteService.getRootJsonResilience()
        val rootComponentJson = SduiRemoteService.getRemoteRootComponent("123")

        val rootComponent = sduiComponentFactory.getComponentInstanceOf(
            componentJson = rootComponentJson ?: rootComponentJsonResilience
        )

        return MacaoResult.Success(rootComponent)
    }
}
