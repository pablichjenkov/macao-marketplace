package com.macaosoftware.sdui.app

import com.macaosoftware.component.core.Component
import com.macaosoftware.sdui.app.data.SduiRemoteService
import com.macaosoftware.sdui.app.di.commonModule
import com.macaosoftware.sdui.app.sdui.SduiComponentFactory
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.DesktopDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.delay
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class JvmRootComponentProvider : RootComponentProvider {
    override suspend fun provideRootComponent(): Component {
        delay(1000)
        val database = createDatabase(DesktopDriverFactory())
        val pluginsModule = module {
            single<Database> { database }
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
