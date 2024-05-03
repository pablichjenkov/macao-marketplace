package com.macaosoftware.sdui.app

import com.macaosoftware.app.RootKoinModuleInitializer
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.sdui.app.di.commonKoinModule
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.IosDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

class IosKoinModuleInitializer(
    private val iosBridge: IosBridge
) : RootKoinModuleInitializer {

    override suspend fun initialize(): List<Module> {

        val database = createDatabase(IosDriverFactory())

        val iOSKoinModule = module {
            single<Database> { database }
            single<AccountPlugin> { iosBridge.accountPlugin }
        }

        return listOf(iOSKoinModule, commonKoinModule)
    }
}
