package com.macaosoftware.sdui.app

import com.macaosoftware.app.RootComponentProvider
import com.macaosoftware.component.core.Component
import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.AuthPluginEmpty
import com.macaosoftware.plugin.JsBridge
import com.macaosoftware.sdui.app.di.commonModule
import com.macaosoftware.sdui.app.sdui.SduiComponentFactory
import com.macaosoftware.sdui.data.SduiRemoteService
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.BrowserDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.delay
import org.koin.dsl.koinApplication
import org.koin.dsl.module

// TODO: Move this to shared
class BrowserRootComponentProvider(
    private val jsBridge: JsBridge
) : RootComponentProvider {

    override suspend fun provideRootComponent(): Component {
        delay(1000)
        val database = createDatabase(BrowserDriverFactory())
        val pluginsModule = module {
            single<Database> { database }
            single<AuthPlugin> { AuthPluginEmpty() }
            /*single<AuthPlugin> { // Not working good
                com.macaosoftware.plugin.auth.AuthPluginGitLive()
            }*/
            // single<AppLifecycleDispatcher> { jsBridge.appLifecycleDispatcher }
        }
        val koinRootContainer = koinApplication {
            printLogger()
            modules(pluginsModule, commonModule)
        }
        val sduiComponentFactory = SduiComponentFactory(koinRootContainer)
        val rootComponentJsonResilience = SduiRemoteService.getRootJsonResilience()
        val rootComponentJson = SduiRemoteService.getRemoteRootComponent("123")

        return sduiComponentFactory.getComponentInstanceOf(
            componentJson = rootComponentJson ?: rootComponentJsonResilience
        )
    }
}
