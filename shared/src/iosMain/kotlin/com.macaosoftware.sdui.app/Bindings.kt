package com.macaosoftware.sdui.app

import com.macaosoftware.component.IosComponentRender
import com.macaosoftware.component.core.Component
import com.macaosoftware.platform.DefaultAppLifecycleDispatcher
import com.macaosoftware.platform.IosBridge
import com.macaosoftware.sdui.app.data.SduiRemoteService
import com.macaosoftware.sdui.app.sdui.SduiComponentFactory
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.DriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.runBlocking
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import platform.UIKit.UIViewController

fun ComponentRenderer(
    rootComponent: Component,
    iosBridge: IosBridge
): UIViewController = IosComponentRender(rootComponent, iosBridge)

fun getSduiRootComponent(): Component = runBlocking {

    val database = createDatabase(DriverFactory())
    val storageModule = module { single<Database> { database } }
    val koinRootContainer = koinApplication {
        printLogger()
        modules(storageModule)
    }
    val sduiComponentFactory = SduiComponentFactory(koinRootContainer)

    val rootComponentJson = SduiRemoteService.getRootJson()
    sduiComponentFactory.getComponentInstanceOf(rootComponentJson)
}

fun createPlatformBridge(): IosBridge {
    return IosBridge(
        appLifecycleDispatcher = DefaultAppLifecycleDispatcher()
    )
}
