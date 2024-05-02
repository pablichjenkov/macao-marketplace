package com.macaosoftware.sdui.app

import androidx.compose.ui.window.CanvasBasedWindow
import com.macaosoftware.app.MacaoKoinApplication
import com.macaosoftware.app.MacaoKoinApplicationState
import com.macaosoftware.app.StartupTaskRunnerDefault
import com.macaosoftware.sdui.app.startup.ComposeAppRootComponentInitializer
import com.macaosoftware.sdui.app.startup.DatabaseMigrationStartupTask
import com.macaosoftware.sdui.app.startup.LaunchDarklyStartupTask
import com.macaosoftware.sdui.app.startup.SdkXyzStartupTask
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {

        val startupTasks = listOf(
            DatabaseMigrationStartupTask(),
            LaunchDarklyStartupTask(),
            SdkXyzStartupTask()
        )

        val applicationState = MacaoKoinApplicationState(
            rootKoinModuleInitializer = JsKoinModuleInitializer(),
            startupTaskRunner = StartupTaskRunnerDefault(startupTasks),
            rootComponentInitializer = ComposeAppRootComponentInitializer()
        )

        CanvasBasedWindow("Macao SDUI JS Demo") {
            MacaoKoinApplication(applicationState = applicationState)
        }
    }
}
