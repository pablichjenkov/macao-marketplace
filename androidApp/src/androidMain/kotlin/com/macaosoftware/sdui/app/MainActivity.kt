package com.macaosoftware.sdui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.macaosoftware.app.MacaoKoinApplicationState
import com.macaosoftware.app.MacaoKoinApplication
import com.macaosoftware.sdui.app.view.SplashScreen
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {

    val applicationState = MacaoKoinApplicationState(
        dispatcher = Dispatchers.IO,
        rootComponentKoinProvider = AndroidRootComponentProvider(this@MainActivity),
        koinModuleInitializer = AndroidKoinModuleInitializer(this@MainActivity)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MacaoKoinApplication(
                onBackPress = { finish() },
                applicationState = applicationState,
                splashScreenContent = { SplashScreen() }
            )
        }
    }
}
