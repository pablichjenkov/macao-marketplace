package com.pablichj.incubator.amadeus.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.macaosoftware.component.AndroidComponentRender
import com.macaosoftware.component.navbar.BottomNavigationComponent
import com.macaosoftware.component.navbar.BottomNavigationComponentDefaults
import com.macaosoftware.platform.AndroidBridge
import com.pablichj.incubator.amadeus.demo.viewmodel.factory.AppBottomNavigationViewModelFactory
import com.pablichj.incubator.amadeus.storage.DriverFactory
import com.pablichj.incubator.amadeus.storage.createDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val androidBridge = AndroidBridge()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val database = createDatabase(DriverFactory(this@MainActivity))
            val navBarComponent = BottomNavigationComponent(
                viewModelFactory = AppBottomNavigationViewModelFactory(
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
