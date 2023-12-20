package com.macaosoftware.sdui.app

import com.macaosoftware.app.RootComponentProvider
import com.macaosoftware.component.core.Component
import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.FirebaseAuth
import com.macaosoftware.plugin.IosBridge
import com.macaosoftware.plugin.PlatformLifecyclePlugin
import com.macaosoftware.plugin.auth.FirebaseAuthPlugin
import com.macaosoftware.sdui.app.di.commonModule
import com.macaosoftware.sdui.app.sdui.SduiComponentFactory
import com.macaosoftware.sdui.data.SduiRemoteService
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.IosDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.delay
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class IosRootComponentProvider(
    private val iosBridge: IosBridge,
    private val firebaseAuth: FirebaseAuth
) : RootComponentProvider {

    override suspend fun provideRootComponent(): Component {
        delay(1000)
        val database = createDatabase(IosDriverFactory())
        val pluginsModule = module {
            single<Database> { database }
            single<PlatformLifecyclePlugin> { iosBridge.platformLifecyclePlugin }
            single<AuthPlugin> { FirebaseAuthPlugin(firebaseAuth) }
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
