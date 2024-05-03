package com.macaosoftware.sdui.app.di

import com.macaosoftware.sdui.app.data.SduiRemoteService
import com.macaosoftware.sdui.app.di.http.getNewDefaultClient
import com.pablichj.incubator.amadeus.common.DefaultTimeProvider
import com.pablichj.incubator.amadeus.common.ITimeProvider
import io.ktor.client.HttpClient
import org.koin.dsl.module

internal val commonKoinModule = module {
    single<ITimeProvider> { DefaultTimeProvider() }
    single<HttpClient> { getNewDefaultClient() }
    single<SduiRemoteService> { SduiRemoteService(get()) }
}
