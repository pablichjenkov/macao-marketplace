package com.macaosoftware.sdui.app.di.http

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val RequestTimeoutMillis = 15_000L

fun getNewDefaultClient(): HttpClient = HttpClient {
    install(HttpTimeout) {
        requestTimeoutMillis = RequestTimeoutMillis
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
        /*filter { request ->
            request.url.host.contains("ktor.io")
        }*/
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
}
