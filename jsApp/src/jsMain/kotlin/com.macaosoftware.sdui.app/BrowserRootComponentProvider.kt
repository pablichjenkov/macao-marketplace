package com.macaosoftware.sdui.app

import com.macaosoftware.component.core.Component
import com.macaosoftware.platform.JsBridge
import com.macaosoftware.sdui.app.data.SduiRemoteService
import com.macaosoftware.sdui.app.sdui.SduiComponentFactory
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.BrowserDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.delay
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class BrowserRootComponentProvider(
    private val jsBridge: JsBridge
) : RootComponentProvider {

    override suspend fun provideRootComponent(): Component {
        delay(1000)
        val database = createDatabase(BrowserDriverFactory())
        val pluginsModule = module {
            single<Database> { database }
            // single<AppLifecycleDispatcher> { jsBridge.appLifecycleDispatcher }
        }
        val koinRootContainer = koinApplication {
            printLogger()
            modules(pluginsModule)
        }
        val sduiComponentFactory = SduiComponentFactory(koinRootContainer)
        val rootComponentJsonResilience = SduiRemoteService.getRootJsonResilience()
        val rootComponentJson = SduiRemoteService.getRemoteRootComponent("123")

        return sduiComponentFactory.getComponentInstanceOf(
            componentJson = rootComponentJson ?: rootComponentJsonResilience
        )
    }
}
