package com.macaosoftware.sdui.app

import com.macaosoftware.app.KoinModuleInitializer
import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.AuthPluginEmpty
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.DefaultTimeProvider
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.pablichj.incubator.amadeus.storage.DesktopDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

class JvmKoinModuleInitializer : KoinModuleInitializer {

    override suspend fun initialize(): Module {

        val database = createDatabase(DesktopDriverFactory())

        return module {
            // TODO: Move this to a common module initializer
            single<ITimeProvider> { DefaultTimeProvider() }
            single<Database> { database }
            single<AuthPlugin> { AuthPluginEmpty() }
        }
    }
}
