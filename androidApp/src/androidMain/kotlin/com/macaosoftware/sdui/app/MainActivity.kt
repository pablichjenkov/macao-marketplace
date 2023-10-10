package com.macaosoftware.sdui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.macaosoftware.component.AndroidComponentRender
import com.macaosoftware.component.navbar.BottomNavigationComponent
import com.macaosoftware.component.navbar.BottomNavigationComponentDefaults
import com.macaosoftware.platform.AndroidBridge
import com.macaosoftware.sdui.app.AppBottomSduiHandler
import com.macaosoftware.sdui.app.SduiService
import com.macaosoftware.sdui.app.di.SharedKoinContext
import com.macaosoftware.sdui.app.viewmodel.factory.AppBottomNavigationViewModelFactory
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.storage.DriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.dsl.module

class MainActivity : ComponentActivity() {

    private val rootComponentJson = SduiService().getRootJson()
    private val androidBridge = AndroidBridge()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val database = createDatabase(DriverFactory(this@MainActivity))

            // Init Koin after getting the database instance
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
                    BottomNavigationComponentDefaults.createBottomNavigationStatePresenter(
                        dispatcher = Dispatchers.Main
                    )
                ),
                content = BottomNavigationComponentDefaults.BottomNavigationComponentView
            )
            setContent {
                AndroidComponentRender(
                    rootComponent = navBarComponent,
                    androidBridge = androidBridge,
                    onBackPress = { finishAffinity() }
                )
            }
        }
    }
}
