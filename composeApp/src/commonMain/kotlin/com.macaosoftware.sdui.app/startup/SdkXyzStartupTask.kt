package com.macaosoftware.sdui.app.startup

import com.macaosoftware.app.StartupTask
import com.macaosoftware.app.StartupTaskError
import com.macaosoftware.util.MacaoResult
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent

class SdkXyzStartupTask : StartupTask {
    override fun name(): String {
        return "Sdk XYZ setup"
    }

    override fun shouldShowLoader(): Boolean {
        return true
    }

    override suspend fun initialize(koinComponent: KoinComponent): MacaoResult<Unit, SdkXyzStartupTaskError> {
        return try {
            // todo: Remove this delay
            delay(1000)
            MacaoResult.Success(Unit)
        } catch (ex: Exception) {
            MacaoResult.Error(SdkXyzStartupTaskError())
        }
    }
}

class SdkXyzStartupTaskError : StartupTaskError()
