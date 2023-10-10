package com.macaosoftware.sdui.app.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

abstract class SharedKoinComponent : KoinComponent {
    override fun getKoin(): Koin = SharedKoinContext.koinApplication.koin
}