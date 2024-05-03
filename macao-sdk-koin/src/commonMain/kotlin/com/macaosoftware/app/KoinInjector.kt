package com.macaosoftware.app

import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent

internal class KoinInjector(
    private val koinApplication: KoinApplication
) : KoinComponent {

    override fun getKoin(): Koin = koinApplication.koin
}
