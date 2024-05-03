package com.macaosoftware.sdui.app

import android.app.Activity
import com.macaosoftware.app.RootKoinModuleInitializer
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.FirebaseAccountPlugin
import com.macaosoftware.sdui.app.di.commonKoinModule
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.AndroidDriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

class AndroidKoinModuleInitializer(
    private val activity: Activity
) : RootKoinModuleInitializer {

    override suspend fun initialize(): List<Module> {

        val database = createDatabase(AndroidDriverFactory(activity))

        val androidKoinModule = module {
            single<Database> { database }
            single<AccountPlugin> { FirebaseAccountPlugin() }
            // single<AccountPlugin> { SupabaseAccountPlugin() }
        }

        return listOf(androidKoinModule, commonKoinModule)
    }
}
