package com.macaosoftware.sdui.app

import com.macaosoftware.app.RootKoinModuleInitializer
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginEmpty
import com.macaosoftware.sdui.app.di.commonKoinModule
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.DefaultTimeProvider
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.pablichj.incubator.amadeus.storage.BrowserDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

class JsKoinModuleInitializer : RootKoinModuleInitializer {

    override suspend fun initialize(): List<Module> {

        val database = createDatabase(BrowserDriverFactory())

        val JsKoinModule = module {
            single<ITimeProvider> { DefaultTimeProvider() }
            single<Database> { database }
            single<AccountPlugin> { AccountPluginEmpty() }
        }

        return listOf(JsKoinModule, commonKoinModule)
    }
}
