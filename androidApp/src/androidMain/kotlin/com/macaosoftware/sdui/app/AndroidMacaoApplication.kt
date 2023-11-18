package com.macaosoftware.sdui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.macaosoftware.component.AndroidComponentRender
import com.macaosoftware.component.core.Component
import com.macaosoftware.platform.AndroidBridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AndroidMacaoApplication(
    androidBridge: AndroidBridge,
    onBackPress: () -> Unit,
    rootComponentProvider: RootComponentProvider,
    splashScreenContent: @Composable () -> Unit
) {
    var rootComponent by remember(androidBridge, rootComponentProvider) {
        mutableStateOf<Component?>(null)
    }
    rootComponent.ifNotNull {
        AndroidComponentRender(
            rootComponent = it,
            androidBridge = androidBridge,
            onBackPress = onBackPress
        )
    }.elseIfNull {
        splashScreenContent()
        rememberCoroutineScope().launch {
            rootComponent = withContext(Dispatchers.IO) {
                rootComponentProvider.provideRootComponent()
            }
        }
    }
}
