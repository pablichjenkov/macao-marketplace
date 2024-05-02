package com.macaosoftware.sdui.app

import androidx.compose.ui.window.ComposeUIViewController
import com.macaosoftware.app.MacaoKoinApplication
import com.macaosoftware.app.MacaoKoinApplicationState
import com.macaosoftware.app.StartupTaskRunnerDefault
import com.macaosoftware.plugin.account.FirebaseAccountPlugin
import com.macaosoftware.plugin.account.FirebaseAuthKmpWrapper
import com.macaosoftware.sdui.app.startup.ComposeAppRootComponentInitializer
import com.macaosoftware.sdui.app.startup.DatabaseMigrationStartupTask
import com.macaosoftware.sdui.app.startup.LaunchDarklyStartupTask
import com.macaosoftware.sdui.app.startup.SdkXyzStartupTask
import platform.UIKit.UIViewController

fun buildDemoMacaoApplication(
    iosBridge: IosBridge
): UIViewController = ComposeUIViewController {

    val startupTasks = listOf(
        DatabaseMigrationStartupTask(),
        LaunchDarklyStartupTask(),
        SdkXyzStartupTask()
    )
    val applicationState = MacaoKoinApplicationState(
        rootKoinModuleInitializer = IosKoinModuleInitializer(iosBridge),
        startupTaskRunner = StartupTaskRunnerDefault(startupTasks),
        rootComponentInitializer = ComposeAppRootComponentInitializer()
    )
    MacaoKoinApplication(applicationState = applicationState)
}

fun createPlatformBridge(
    firebaseAuthKmpWrapper: FirebaseAuthKmpWrapper
): IosBridge {

    // val accountPlugin = SupabaseAccountPlugin()
    val accountPlugin = FirebaseAccountPlugin(firebaseAuthKmpWrapper)

    return IosBridge(
        accountPlugin = accountPlugin
    )
}
