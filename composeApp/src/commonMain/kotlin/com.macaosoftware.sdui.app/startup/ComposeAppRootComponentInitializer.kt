package com.macaosoftware.sdui.app.startup

import com.macaosoftware.app.RootComponentInitializer
import com.macaosoftware.component.core.Component
import com.macaosoftware.sdui.app.data.SduiRemoteService
import com.macaosoftware.sdui.app.domain.MacaoApiError
import com.macaosoftware.sdui.app.domain.ViewModelFactory
import com.macaosoftware.util.MacaoResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class ComposeAppRootComponentInitializer : RootComponentInitializer {

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(
        koinComponent: KoinComponent
    ): MacaoResult<Component>  = try {

        val sduiRemoteService = koinComponent.get<SduiRemoteService>()

        //val sduiComponentFactory = SduiComponentFactory(koinComponent)
        val viewModelFactory = koinComponent.get<ViewModelFactory>()

        //koinComponent.getKoin().setProperty("", sduiComponentFactory)

        val rootComponentJsonResilience = sduiRemoteService.getRootJsonResilience()
        val rootComponentJson = sduiRemoteService.getRemoteRootComponent("123")

        val rootComponent = viewModelFactory.getComponentInstanceOf(
            componentJson = rootComponentJson ?: rootComponentJsonResilience
        )

        MacaoResult.Success(rootComponent)
    } catch (th: Throwable) {
        MacaoResult.Error(
            MacaoApiError(errorCode = 1,  errorDescription = th.toString())
        )
    }
}
