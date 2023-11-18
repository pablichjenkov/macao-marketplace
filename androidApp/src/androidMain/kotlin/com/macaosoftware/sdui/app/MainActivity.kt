package com.macaosoftware.sdui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.macaosoftware.platform.AndroidBridge

class MainActivity : ComponentActivity() {

    private val androidBridge = AndroidBridge()
    private val rootComponentProvider = AndroidRootComponentProvider(
        this@MainActivity,
        androidBridge
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidMacaoApplication(
                androidBridge = androidBridge,
                onBackPress = { finish() },
                rootComponentProvider = rootComponentProvider
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Example of Android Splash Screen"
                    )
                }
            }
        }
    }
}
