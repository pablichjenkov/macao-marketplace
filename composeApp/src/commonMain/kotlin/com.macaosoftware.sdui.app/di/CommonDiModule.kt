package com.macaosoftware.sdui.app.di

import com.macaosoftware.sdui.app.data.SduiRemoteService
import com.macaosoftware.sdui.app.di.http.createDefaultHttpClient
import com.macaosoftware.sdui.app.domain.ViewModelFactory
import com.pablichj.incubator.amadeus.common.DefaultTimeProvider
import com.pablichj.incubator.amadeus.common.ITimeProvider
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val commonKoinModule = module {
    single<ITimeProvider> {
        DefaultTimeProvider()
    }
    single<HttpClient> {
        createDefaultHttpClient()
    }
    singleOf(::SduiRemoteService)

    single<ViewModelFactory> {
        ViewModelFactory(getKoin())
    }

}
