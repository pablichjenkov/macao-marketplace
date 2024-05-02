package com.macaosoftware.sdui.app

import android.app.Activity
import com.macaosoftware.app.RootKoinModuleInitializer
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.FirebaseAccountPlugin
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.DefaultTimeProvider
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.pablichj.incubator.amadeus.storage.AndroidDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

class AndroidKoinModuleInitializer(
    private val activity: Activity
) : RootKoinModuleInitializer {

    override suspend fun initialize(): Module {

        val database = createDatabase(AndroidDriverFactory(activity))

        return module {
            single<ITimeProvider> { DefaultTimeProvider() }
            single<Database> { database }
            single<AccountPlugin> { FirebaseAccountPlugin() }
            //single<AccountPlugin> { SupabaseAccountPlugin() }
        }
    }
}
