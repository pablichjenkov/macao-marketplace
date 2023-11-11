package com.macaosoftware.sdui.app

import com.macaosoftware.component.core.Component
import com.macaosoftware.platform.AppLifecycleDispatcher
import com.macaosoftware.platform.IosBridge
import com.macaosoftware.sdui.app.data.SduiRemoteService
import com.macaosoftware.sdui.app.sdui.SduiComponentFactory
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.DriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.delay
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class IosRooComponentProvider(
    private val iosBridge: IosBridge
) : RootComponentProvider {
    override suspend fun provideRootComponent(): Component {
        delay(2000)
        val database = createDatabase(DriverFactory())
        val storageModule = module { single<Database> { database } }
        val platformLifecycleModule = module {
            single<AppLifecycleDispatcher> { iosBridge.appLifecycleDispatcher }
        }
        val koinRootContainer = koinApplication {
            printLogger()
            modules(storageModule, platformLifecycleModule)
        }
        val sduiComponentFactory = SduiComponentFactory(koinRootContainer)
        val rootComponentJsonResilience = SduiRemoteService.getRootJson()
        val rootComponentJson = SduiRemoteService.getRemoteRootComponent("123")

        return sduiComponentFactory.getComponentInstanceOf(
            componentJson = rootComponentJson ?: rootComponentJsonResilience
        )
    }
}
