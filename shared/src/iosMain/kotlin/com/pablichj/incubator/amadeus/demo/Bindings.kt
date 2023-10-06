package com.pablichj.incubator.amadeus.demo

import com.macaosoftware.component.IosComponentRender
import com.macaosoftware.component.core.Component
import com.macaosoftware.component.navbar.BottomNavigationComponent
import com.macaosoftware.component.navbar.BottomNavigationComponentDefaults
import com.macaosoftware.platform.DefaultAppLifecycleDispatcher
import com.macaosoftware.platform.IosBridge
import com.pablichj.incubator.amadeus.demo.viewmodel.factory.AppBottomNavigationViewModelFactory
import com.pablichj.incubator.amadeus.storage.DriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import platform.UIKit.UIViewController

fun ComponentRenderer(
    rootComponent: Component,
    iosBridge: IosBridge
): UIViewController = IosComponentRender(rootComponent, iosBridge)

fun buildAmadeusDemoComponent(): Component {
    return runBlocking {
        val database = createDatabase(DriverFactory())
        BottomNavigationComponent(
            viewModelFactory = AppBottomNavigationViewModelFactory(
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