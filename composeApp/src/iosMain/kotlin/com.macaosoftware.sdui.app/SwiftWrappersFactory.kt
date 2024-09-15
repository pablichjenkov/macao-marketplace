package com.macaosoftware.sdui.app

import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginEmpty
import com.macaosoftware.plugin.account.AccountPluginSwiftWrapperBase
import com.macaosoftware.plugin.account.createAccountPlugin

/**
 * This class serves as the bridge between Swift and Kotlin. Swift and Kotlin
 * cannot directly interoperate with each other but an interface in Kotlin gets
 * transpiled to a protocol in objective-c which swift perfectly understand.
 * We can use this technique then to bridge between the two languages. Defining
 * an interface in Kotlin and providing the implementation in swift.
 * These class is basically a collection of all those swift implementations.
 * It gets setup in the swift Application and is passed to Kotlin when the
 * UiViewController is created. Then this is hooked into the Koin root module
 * so it can be injected across the the whole App.
 * */
@ObjCName(name = "SwiftWrappersFactory", exact = true)
class SwiftWrappersFactory {

    var accountPluginSwiftWrapperBase: AccountPluginSwiftWrapperBase? = null

    fun provideAccountPlugin(): AccountPlugin {
        return accountPluginSwiftWrapperBase?.let {
            createAccountPlugin(it)
        } ?: AccountPluginEmpty()
    }

}