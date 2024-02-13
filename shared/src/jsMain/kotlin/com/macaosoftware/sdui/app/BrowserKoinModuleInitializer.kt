package com.macaosoftware.sdui.app

import com.macaosoftware.app.KoinRootModuleInitializer
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginEmpty
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.DefaultTimeProvider
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.pablichj.incubator.amadeus.storage.BrowserDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

class BrowserKoinModuleInitializer : KoinRootModuleInitializer {

    override suspend fun initialize(): Module {

        val database = createDatabase(BrowserDriverFactory())

        return module {
            single<ITimeProvider> { DefaultTimeProvider() }
            single<Database> { database }
            single<AccountPlugin> { AccountPluginEmpty() }
            // single<AppLifecycleDispatcher> { jsBridge.appLifecycleDispatcher }
        }
    }
}
