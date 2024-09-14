package com.macaosoftware.sdui.app

import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginEmpty
import com.macaosoftware.plugin.account.AccountPluginSwiftWrapperBase
import com.macaosoftware.plugin.account.createAccountPlugin

@ObjCName(name = "SwiftWrappersFactory", exact = true)
class SwiftWrappersFactory {

    var accountPluginSwiftWrapperBase: AccountPluginSwiftWrapperBase? = null

    fun provideAccountPlugin(): AccountPlugin {
        return accountPluginSwiftWrapperBase?.let {
            createAccountPlugin(it)
        } ?: AccountPluginEmpty()
    }

}