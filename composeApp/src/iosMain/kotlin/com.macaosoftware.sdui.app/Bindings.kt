package com.macaosoftware.sdui.app

import androidx.compose.ui.window.ComposeUIViewController
import com.macaosoftware.app.MacaoKoinApplication
import com.macaosoftware.app.MacaoKoinApplicationState
import com.macaosoftware.app.StartupTaskRunnerDefault
import com.macaosoftware.plugin.AppTheme
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginSwiftWrapperBase
import com.macaosoftware.plugin.account.createAccountPlugin
import com.macaosoftware.sdui.app.startup.ComposeAppRootComponentInitializer
import com.macaosoftware.sdui.app.startup.DatabaseMigrationStartupTask
import com.macaosoftware.sdui.app.startup.LaunchDarklyStartupTask
import com.macaosoftware.sdui.app.startup.SdkXyzStartupTask
import platform.UIKit.UIViewController

fun buildDemoMacaoApplication(
    swiftWrappersFactory: SwiftWrappersFactory
): UIViewController = ComposeUIViewController {

    val startupTasks = listOf(
        DatabaseMigrationStartupTask(),
        LaunchDarklyStartupTask(),
        SdkXyzStartupTask()
    )
    val applicationState = MacaoKoinApplicationState(
        rootKoinModuleInitializer = IosKoinModuleInitializer(swiftWrappersFactory),
        startupTaskRunner = StartupTaskRunnerDefault(startupTasks),
        rootComponentInitializer = ComposeAppRootComponentInitializer()
    )

    AppTheme {
        MacaoKoinApplication(applicationState = applicationState)
    }
}
