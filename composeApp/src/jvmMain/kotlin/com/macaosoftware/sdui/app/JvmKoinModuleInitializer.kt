package com.macaosoftware.sdui.app

import com.macaosoftware.app.RootModuleKoinInitializer
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.SupabaseAccountPlugin
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.DefaultTimeProvider
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.pablichj.incubator.amadeus.storage.DesktopDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

class JvmKoinModuleInitializer : RootModuleKoinInitializer {

    override suspend fun initialize(): Module {

        val database = createDatabase(DesktopDriverFactory())

        return module {
            single<ITimeProvider> { DefaultTimeProvider() }
            single<Database> { database }
            single<AccountPlugin> { SupabaseAccountPlugin() }
        }
    }
}
