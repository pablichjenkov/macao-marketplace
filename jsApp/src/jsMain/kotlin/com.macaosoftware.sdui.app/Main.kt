package com.macaosoftware.sdui.app

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
import com.macaosoftware.sdui.app.AppBottomSduiHandler
import com.macaosoftware.sdui.app.SduiService
import com.macaosoftware.sdui.app.di.SharedKoinContext
import com.pablichj.incubator.amadeus.Database
import com.macaosoftware.sdui.app.viewmodel.factory.AppBottomNavigationViewModelFactory
import com.pablichj.incubator.amadeus.storage.DriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.Dispatchers
import org.jetbrains.skiko.wasm.onWasmReady
import org.koin.dsl.module

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {

        val rootComponentJson = SduiService().getRootJson()
        val jsBridge = JsBridge()

        CanvasBasedWindow("Amadeus API Demo") {
            Text("Loading SQDelight")
            val database = remember(Unit) { mutableStateOf<Database?>(null) }
            val databaseCopy = database.value

            if (databaseCopy != null) {
                println("JS_Main::onWasmReady databaseCopy != null")
                Text("Loading SQDelight Success")

                // Init Koin after getting the database instance
                val storageModule = module { single <Database> { databaseCopy } }
                /*startKoin {
                    modules(storageModule)
                }*/
                SharedKoinContext.initKoin(
                    listOf(storageModule)
                )
                val navBarComponent = remember {
                    BottomNavigationComponent(
                        viewModelFactory = AppBottomNavigationViewModelFactory(
                            sduiHandler = AppBottomSduiHandler(rootComponentJson),
                            database = databaseCopy,
                            bottomNavigationStatePresenter = BottomNavigationComponentDefaults.createBottomNavigationStatePresenter(
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

    }
}
