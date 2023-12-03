package com.macaosoftware.sdui.app

import android.content.Context
import com.macaosoftware.component.core.Component
import com.macaosoftware.sdui.app.data.SduiRemoteService
import com.macaosoftware.sdui.app.di.commonModule
import com.macaosoftware.sdui.app.plugin.AuthPlugin
import com.macaosoftware.sdui.app.plugin.FirebaseAuthPluginAndroid
import com.macaosoftware.sdui.app.sdui.SduiComponentFactory
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.AndroidDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.delay
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class AndroidRootComponentProvider(
    private val context: Context,
) : RootComponentProvider {

    override suspend fun provideRootComponent(): Component {
        delay(1000)
        val database = createDatabase(AndroidDriverFactory(context))
        val pluginsModule = module {
            single<Database> { database }
            single<AuthPlugin> { FirebaseAuthPluginAndroid() }
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
