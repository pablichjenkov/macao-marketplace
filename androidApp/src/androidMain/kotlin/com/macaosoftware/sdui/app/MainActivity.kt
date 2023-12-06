package com.macaosoftware.sdui.app

import AuthPluginAndroid
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.macaosoftware.plugin.MacaoApplicationState
import kotlinx.coroutines.Dispatchers
import android.content.Intent
import android.net.Uri
import com.google.firebase.Firebase

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
                macaoApplicationState = macaoApplicationState
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
