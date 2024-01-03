package com.macaosoftware.sdui.app

import com.macaosoftware.app.RootComponentKoinProvider
import com.macaosoftware.component.core.Component
import com.macaosoftware.plugin.JsBridge
import com.macaosoftware.sdui.app.sdui.SduiComponentFactory
import com.macaosoftware.sdui.data.SduiRemoteService
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent

// TODO: Move this to shared
class BrowserRootComponentProvider(
    private val jsBridge: JsBridge
) : RootComponentKoinProvider {

    override suspend fun provideRootComponent(koinComponent: KoinComponent): Component {

        delay(1000)

        val sduiComponentFactory = SduiComponentFactory(koinComponent)
        val rootComponentJsonResilience = SduiRemoteService.getRootJsonResilience()
        val rootComponentJson = SduiRemoteService.getRemoteRootComponent("123")

        return sduiComponentFactory.getComponentInstanceOf(
            componentJson = rootComponentJson ?: rootComponentJsonResilience
        )
    }
}
