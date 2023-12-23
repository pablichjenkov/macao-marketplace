package com.macaosoftware.sdui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.macaosoftware.app.AndroidMacaoApplication
import com.macaosoftware.app.MacaoApplicationState
import com.macaosoftware.sdui.app.view.SplashScreen
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {

    private val rootComponentProvider = AndroidRootComponentProvider(
        this@MainActivity,
    )
    val macaoApplicationState = MacaoApplicationState(
        Dispatchers.IO,
        rootComponentProvider
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidMacaoApplication(
                onBackPress = { finish() },
                macaoApplicationState = macaoApplicationState,
                splashScreenContent = { SplashScreen() }
            )
        }
    }
}
