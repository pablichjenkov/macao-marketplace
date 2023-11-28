package com.macaosoftware.sdui.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Notification
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberNotification
import androidx.compose.ui.window.singleWindowApplication
import com.macaosoftware.plugin.DesktopBridge
import com.macaosoftware.sdui.app.plugin.MacaoApplicationState
import kotlinx.coroutines.Dispatchers
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon
import kotlin.system.exitProcess

fun main() {

    val desktopBridge = DesktopBridge()
    val windowState = WindowState(size = DpSize(800.dp, 600.dp))
    val macaoApplicationState = MacaoApplicationState(
        Dispatchers.IO,
        JvmRootComponentProvider()
    )



    singleWindowApplication(
        title = "Macao SDUI Demo",
        state = windowState,
        undecorated = true
    ) {
        WindowWithCustomTopDecoration(
            onMinimizeClick = { windowState.isMinimized = true },
            onMaximizeClick = { windowState.isMinimized = false },
            onCloseClick = { exitProcess(0) },
            onRefreshClick = {
                macaoApplicationState.fetchRootComponent()
            },
            onBackClick = {
                desktopBridge.backPressDispatcherPlugin.dispatchBackPressed()
            }
        ) {
            val notification = rememberNotification(
                title = "Macao Sdui App",
                message = "Welcome toMacao Sdui App developed by Pablo Valdes & Muhammad Khubaib Imtiaz",
                type = Notification.Type.Info
            )

            val refreshIcon = rememberVectorPainter(image = Icons.Default.Refresh)
            val exitIcon = rememberVectorPainter(image = Icons.Default.ExitToApp)
            val lightThemeIcon = rememberVectorPainter(image = Icons.Default.LightMode)
            val darkThemeIcon = rememberVectorPainter(image = Icons.Default.DarkMode)

            MenuBar {
                Menu(text = "File", mnemonic = 'F', enabled = true) {
                    Item(
                        text = "Refresh",
                        mnemonic = 'R',
                        enabled = true,
                        icon = refreshIcon,
                        shortcut = KeyShortcut(
                            key = Key.R,
                            ctrl = true,
                            meta = true,
                            alt = false,
                            shift = false
                        )
                    ) {
                        showNotification(notification.title, notification.message)
                    }

                    Item(
                        text = "Exit",
                        mnemonic = 'E',
                        enabled = true,
                        icon = exitIcon,
                        shortcut = KeyShortcut(
                            key = Key.Escape,
                            ctrl = true,
                            meta = true,
                            alt = false,
                            shift = false
                        )
                    ) {

                    }
                }

                Menu(text = "Setting", mnemonic = 'S', enabled = true) {
                    Item(
                        text = "Light Theme",
                        mnemonic = 'L',
                        enabled = true,
                        icon = lightThemeIcon,
                        shortcut = KeyShortcut(
                            key = Key.R,
                            ctrl = true,
                            meta = true,
                            alt = false,
                            shift = false
                        )
                    ) {
                        showNotification(notification.title, notification.message)
                    }

                    Item(
                        text = "Dark Theme",
                        mnemonic = 'D',
                        enabled = true,
                        icon = darkThemeIcon,
                        shortcut = KeyShortcut(
                            key = Key.Escape,
                            ctrl = true,
                            meta = true,
                            alt = false,
                            shift = false
                        )
                    ) {

                    }
                }
            }
            JvmMacaoApplication(
                windowState = windowState,
                desktopBridge = desktopBridge,
                onBackPress = { exitProcess(0) },
                macaoApplicationState = macaoApplicationState
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Example of JVM Splash Screen"
                    )
                }
            }
        }
    }
}

fun showNotification(title: String, message: String) {
    if (SystemTray.isSupported()) {
        val tray = SystemTray.getSystemTray()
        val image = Toolkit.getDefaultToolkit().getImage("logo.png")
        val trayIcon = TrayIcon(image, "Notification")
        trayIcon.isImageAutoSize = true
        trayIcon.toolTip = "Notification"
        tray.add(trayIcon)

        trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO)
    }
}