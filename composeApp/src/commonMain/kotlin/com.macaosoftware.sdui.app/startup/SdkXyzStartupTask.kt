package com.macaosoftware.sdui.app.startup

import com.macaosoftware.app.KoinInjector
import com.macaosoftware.app.StartupTask
import com.macaosoftware.util.MacaoResult
import kotlinx.coroutines.delay

class SdkXyzStartupTask : StartupTask {
    override fun name(): String {
        return "Sdk XYZ setup"
    }

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(koinInjector: KoinInjector): MacaoResult<Unit> {
        // todo: Remove thios delay
        delay(1000)
        return MacaoResult.Success(Unit)
    }
}