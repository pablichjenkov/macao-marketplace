package com.macaosoftware.sdui.app

import com.macaosoftware.app.KoinModuleInitializer
import com.macaosoftware.plugin.IosBridge
import com.macaosoftware.plugin.PlatformLifecyclePlugin
import com.macaosoftware.plugin.account.AccountPlugin
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.DefaultTimeProvider
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.pablichj.incubator.amadeus.storage.IosDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

class IosKoinModuleInitializer(
    private val iosBridge: IosBridge
) : KoinModuleInitializer {

    override suspend fun initialize(): Module {

        val database = createDatabase(IosDriverFactory())

        return module {
            single<ITimeProvider> { DefaultTimeProvider() }

            single<Database> { database }
            single<PlatformLifecyclePlugin> { iosBridge.platformLifecyclePlugin }
            single<AccountPlugin> { iosBridge.accountPlugin }
        }
    }
}
