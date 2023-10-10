package com.macaosoftware.sdui.app.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.koinApplication

object SharedKoinContext {

    lateinit var koinApplication: KoinApplication

    fun initKoin(
        modules: List<Module>
    ) {
        koinApplication = koinApplication {
            modules(modules)
            printLogger()
        }
    }
}
