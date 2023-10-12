package com.macaosoftware.sdui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.macaosoftware.component.DesktopComponentRender
import com.macaosoftware.platform.DesktopBridge
import com.macaosoftware.sdui.app.data.SduiRemoteService
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.DriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.runBlocking
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import kotlin.system.exitProcess

fun main() {

    val database = runBlocking { createDatabase(DriverFactory()) }
    val storageModule = module { single<Database> { database } }
    val koinRootContainer = koinApplication {
        printLogger()
        modules(storageModule)
    }
    val sduiComponentFactory = SduiComponentFactory(koinRootContainer)
    val windowState = WindowState(size = DpSize(500.dp, 800.dp))
    val desktopBridge = DesktopBridge()
    val rootComponentJson = SduiRemoteService.getRootJson()
    val rootComponent = sduiComponentFactory.getComponentInstanceOf(rootComponentJson)

    singleWindowApplication(
        title = "Amadeus Desktop Demo",
        state = windowState,
        undecorated = true
    ) {
        MaterialTheme {
            Column {
                WindowDraggableArea {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(Color.LightGray)
                    ) {
                        Row {
                            Spacer(modifier = Modifier.size(20.dp))
                            Box(
                                modifier = Modifier
                                    .padding(top = 14.dp, end = 8.dp)
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(Color.Red)
                                    .clickable {
                                        exitProcess(0)
                                    }
                            )
                            Box(
                                modifier = Modifier
                                    .padding(top = 14.dp, end = 8.dp)
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(Color.Yellow)
                                    .clickable {
                                        windowState.isMinimized = true
                                    }
                            )
                            Box(
                                modifier = Modifier
                                    .padding(top = 14.dp)
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(Color.Green)
                                    .clickable {
                                        // Add code to maximize the window
                                    }
                            )
                        }
                        Row(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "back"
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                        }

                    }
                }
                DesktopComponentRender(
                    rootComponent = rootComponent,
                    windowState = windowState,
                    desktopBridge = desktopBridge
                )
            }
        }
    }
}
