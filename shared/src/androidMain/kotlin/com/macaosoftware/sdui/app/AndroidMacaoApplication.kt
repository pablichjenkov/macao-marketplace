package com.macaosoftware.sdui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.macaosoftware.component.AndroidComponentRender
import com.macaosoftware.plugin.MacaoApplicationState
import com.macaosoftware.plugin.PermissionsViewModel
import com.macaosoftware.plugin.elseIfNull
import com.macaosoftware.plugin.ifNotNull
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory

@Composable
fun AndroidMacaoApplication(
    onBackPress: () -> Unit,
    macaoApplicationState: MacaoApplicationState,
    splashScreenContent: @Composable () -> Unit
) {
    /* In this way, You can use it. Place where ever you want. Test and Let me Know
    If you face any error. You bind effect to bind permission*/
    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val controller: PermissionsController = remember(factory) { factory.createPermissionsController() }

    val viewModel = remember {
        PermissionsViewModel(controller)
    }
    val rootComponent = macaoApplicationState.rootComponentState.value
    rootComponent.ifNotNull {
        AndroidComponentRender(
            rootComponent = it,
            onBackPress = onBackPress
        )
    }.elseIfNull {
        splashScreenContent()
        macaoApplicationState.fetchRootComponent()
    }
}
