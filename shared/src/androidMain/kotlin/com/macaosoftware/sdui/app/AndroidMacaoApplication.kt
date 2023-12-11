package com.macaosoftware.sdui.app

import androidx.compose.runtime.Composable
import com.macaosoftware.component.AndroidComponentRender
import com.macaosoftware.plugin.MacaoApplicationState
import com.macaosoftware.plugin.elseIfNull
import com.macaosoftware.plugin.ifNotNull

@Composable
fun AndroidMacaoApplication(
    onBackPress: () -> Unit,
    macaoApplicationState: MacaoApplicationState,
    splashScreenContent: @Composable () -> Unit
) {
    /* Use below for the permission request
     val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
     val controller: PermissionsController = remember(factory) { factory.createPermissionsController() }
     val coroutineScope: CoroutineScope = rememberCoroutineScope()
     val viewModel : PermissionViewModel = remember {PermissionViewModel(controller)}
     BindEffect(permissionController: controller)
 */
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
