package com.pablichj.incubator.amadeus.demo

import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.macaosoftware.component.BrowserComponentRender
import com.macaosoftware.component.navbar.BottomNavigationComponent
import com.macaosoftware.component.navbar.BottomNavigationComponentDefaults
import com.macaosoftware.platform.JsBridge
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.demo.viewmodel.factory.AppBottomNavigationViewModelFactory
import com.pablichj.incubator.amadeus.storage.DriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.Dispatchers
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {

        val jsBridge = JsBridge()

        CanvasBasedWindow("Amadeus API Demo") {
            Text("Loading SQDelight")
            val database = remember(Unit) { mutableStateOf<Database?>(null) }

            val databaseCopy = database.value

            if (databaseCopy != null) {
                println("JS_Main::onWasmReady databaseCopy != null")
                Text("Loading SQDelight Success")
                val navBarComponent = remember {
                    BottomNavigationComponent(
                        viewModelFactory = AppBottomNavigationViewModelFactory(
                            database = databaseCopy,
                            BottomNavigationComponentDefaults.createBottomNavigationStatePresenter(
                                dispatcher = Dispatchers.Main
                            )
                        ),
                        content = BottomNavigationComponentDefaults.BottomNavigationComponentView
                    )
                }
                BrowserComponentRender(
                    rootComponent = navBarComponent,
                    jsBridge = jsBridge,
                    onBackPress = {
                        println("Back press dispatched in root node")
                    }
                )
            } else {
                println("JS_Main::onWasmReady databaseCopy == null")
                Text("Loading SQDelight Failed")
                LaunchedEffect(Unit) {
                    println("JS_Main::onWasmReady.LaunchedEffect")
                    database.value = createDatabase(DriverFactory())
                }
            }

        }

        /*
                BrowserViewportWindow("Amadeus API Demo") {
                    val database = remember(key1 = Unit) { mutableStateOf<Database?>(null) }
                    val databaseCopy = database.value

                    if (databaseCopy != null) {
                        val hotelDemoComponent = TreeBuilder.getRootComponent(databaseCopy)

                        BrowserComponentRender(
                            rootComponent = hotelDemoComponent,
                            onBackPress = {
                                println("Back press dispatched in root node")
                            }
                        )

                    } else {
                        Text("Loading SQDelight")
                    }
                    LaunchedEffect(key1 = Unit) {
                        database.value = createDatabase(DriverFactory())
                    }
                }
        */
    }
}

