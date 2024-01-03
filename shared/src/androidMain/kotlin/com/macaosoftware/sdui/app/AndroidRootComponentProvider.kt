package com.macaosoftware.sdui.app

import android.content.Context
import com.macaosoftware.app.RootComponentKoinProvider
import com.macaosoftware.component.core.Component
import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.sdui.app.di.commonModule
import com.macaosoftware.sdui.app.sdui.SduiComponentFactory
import com.macaosoftware.sdui.data.SduiRemoteService
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.AndroidDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class AndroidRootComponentProvider(
    private val context: Context,
) : RootComponentKoinProvider {

    override suspend fun provideRootComponent(
        koinComponent: KoinComponent
    ): Component {

        delay(1000)

        val sduiComponentFactory = SduiComponentFactory(koinComponent)
        val rootComponentJsonResilience = SduiRemoteService.getRootJsonResilience()
        val rootComponentJson = SduiRemoteService.getRemoteRootComponent(ownerId = "123")

        return sduiComponentFactory.getComponentInstanceOf(
            componentJson = rootComponentJson ?: rootComponentJsonResilience
        )
    }
}
