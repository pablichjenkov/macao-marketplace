package com.macaosoftware.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.window.WindowState
import com.macaosoftware.component.DesktopComponentRender

@Composable
fun MacaoKoinApplication(
    windowState: WindowState,
    applicationState: MacaoKoinApplicationState
) {

    when (val stage = applicationState.stage.value) {

        Stage.Created -> {
            SideEffect {
                applicationState.start()
            }
        }

        Stage.InitializingDiAndRootComponent -> {
        }

        is Stage.Started -> {
            DesktopComponentRender(
                rootComponent = stage.rootComponent,
                windowState = windowState
            )
        }
    }
}
