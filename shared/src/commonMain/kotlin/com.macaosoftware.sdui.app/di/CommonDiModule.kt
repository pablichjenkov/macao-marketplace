package com.macaosoftware.sdui.app.di

import com.pablichj.incubator.amadeus.common.DefaultTimeProvider
import com.pablichj.incubator.amadeus.common.ITimeProvider
import org.koin.dsl.module

val commonModule = module {
    single<ITimeProvider> { DefaultTimeProvider() }
}