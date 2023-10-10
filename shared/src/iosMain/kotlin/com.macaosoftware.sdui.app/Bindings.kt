package com.macaosoftware.sdui.app

import com.macaosoftware.component.IosComponentRender
import com.macaosoftware.component.core.Component
import com.macaosoftware.component.navbar.BottomNavigationComponent
import com.macaosoftware.component.navbar.BottomNavigationComponentDefaults
import com.macaosoftware.platform.DefaultAppLifecycleDispatcher
import com.macaosoftware.platform.IosBridge
import com.macaosoftware.sdui.app.AppBottomSduiHandler
import com.macaosoftware.sdui.app.SduiService
import com.macaosoftware.sdui.app.di.SharedKoinContext
import com.macaosoftware.sdui.app.viewmodel.factory.AppBottomNavigationViewModelFactory
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.DriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.dsl.module
import platform.UIKit.UIViewController

fun ComponentRenderer(
    rootComponent: Component,
    iosBridge: IosBridge
): UIViewController = IosComponentRender(rootComponent, iosBridge)

fun buildAmadeusDemoComponent(): Component {
    val rootComponentJson = SduiService().getRootJson()
    return runBlocking {
        val database = createDatabase(DriverFactory())

        val storageModule = module { single <Database> { database } }
        /*startKoin {
            modules(storageModule)
        }*/
        SharedKoinContext.initKoin(
            listOf(storageModule)
        )
        BottomNavigationComponent(
            viewModelFactory = AppBottomNavigationViewModelFactory(
                sduiHandler = AppBottomSduiHandler(rootComponentJson),
                database = database,
                BottomNavigationComponentDefaults.createBottomNavigationStatePresenter(
                    dispatcher = Dispatchers.Main
                )
            ),
            content = BottomNavigationComponentDefaults.BottomNavigationComponentView
        )
    }
}

fun createPlatformBridge(): IosBridge {
    return IosBridge(
        appLifecycleDispatcher = DefaultAppLifecycleDispatcher()
    )
}
