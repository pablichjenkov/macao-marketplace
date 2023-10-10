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
import com.macaosoftware.component.navbar.BottomNavigationComponent
import com.macaosoftware.component.navbar.BottomNavigationComponentDefaults
import com.macaosoftware.platform.DesktopBridge
import com.macaosoftware.sdui.app.di.SharedKoinContext
import com.macaosoftware.sdui.app.viewmodel.factory.AppBottomNavigationViewModelFactory
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.DriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.putJsonArray
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import kotlin.system.exitProcess

fun main() {

    val rootComponentJson = SduiService().getRootJson()

    val windowState = WindowState(size = DpSize(500.dp, 800.dp))

    val database = runBlocking { createDatabase(DriverFactory()) }

    val storageModule = module { single <Database> { database } }
    /*startKoin {
        modules(storageModule)
    }*/
    SharedKoinContext.initKoin(
        listOf(storageModule)
    )

    val navBarComponent = BottomNavigationComponent(
        viewModelFactory = AppBottomNavigationViewModelFactory(
            sduiHandler = AppBottomSduiHandler(rootComponentJson),
            database = database,
            bottomNavigationStatePresenter = BottomNavigationComponentDefaults.createBottomNavigationStatePresenter(
                dispatcher = Dispatchers.Main
            )
        ),
        content = BottomNavigationComponentDefaults.BottomNavigationComponentView
    )
    val desktopBridge = DesktopBridge()

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
                    rootComponent = navBarComponent,
                    windowState = windowState,
                    desktopBridge = desktopBridge
                )
            }
        }
    }
}