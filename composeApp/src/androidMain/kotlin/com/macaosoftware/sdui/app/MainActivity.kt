package com.macaosoftware.sdui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.macaosoftware.app.MacaoKoinApplication
import com.macaosoftware.app.MacaoKoinApplicationState
import com.macaosoftware.app.StartupTaskRunnerDefault
import com.macaosoftware.sdui.app.startup.ComposeAppRootComponentInitializer
import com.macaosoftware.sdui.app.startup.DatabaseMigrationStartupTask
import com.macaosoftware.sdui.app.startup.LaunchDarklyStartupTask
import com.macaosoftware.sdui.app.startup.SdkXyzStartupTask
import com.macaosoftware.sdui.app.theme.AppTheme

class MainActivity : ComponentActivity() {

    val startupTasks = listOf(
        DatabaseMigrationStartupTask(),
        LaunchDarklyStartupTask(),
        SdkXyzStartupTask()
    )

    val applicationState = MacaoKoinApplicationState(
        rootKoinModuleInitializer = AndroidKoinModuleInitializer(this),
        startupTaskRunner = StartupTaskRunnerDefault(startupTasks),
        rootComponentInitializer = ComposeAppRootComponentInitializer()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MacaoKoinApplication(applicationState = applicationState)
            }
        }
    }
}
