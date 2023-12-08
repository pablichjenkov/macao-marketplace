package com.macaosoftware.plugin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.launch

class PermissionsViewModel(private val permissionsController: PermissionsController) : ViewModel(),
    PermissionsHandler {

    override fun onImagePermissionPressed() {
        requestPermission(Permission.CAMERA, Permission.GALLERY)
    }

    override fun onVideoPermissionPressed() {
        requestPermission(Permission.CAMERA, Permission.RECORD_AUDIO)
    }

    override fun onNotificationPermissionPressed() {
        requestPermission(Permission.REMOTE_NOTIFICATION)
    }

    override fun onStoragePermissionPressed() {
        requestPermission(Permission.STORAGE, Permission.WRITE_STORAGE)
    }

    override fun onLocationPermissionPressed() {
        requestPermission(Permission.LOCATION)
    }

    override fun onCoarseLocationPermissionPressed() {
        requestPermission(Permission.COARSE_LOCATION)
    }

    override fun onRemoteNotificationPermissionPressed() {
        requestPermission(Permission.REMOTE_NOTIFICATION)
    }

    override fun onRecordAudioPermissionPressed() {
        requestPermission(Permission.RECORD_AUDIO)
    }

    override fun onBluetoothAdvertisePermissionPressed() {
        requestPermission(
            Permission.BLUETOOTH_ADVERTISE,
            Permission.BLUETOOTH_LE,
            Permission.BLUETOOTH_CONNECT,
            Permission.BLUETOOTH_SCAN
        )
    }

    override fun onMotionPermissionPressed() {
        requestPermission(Permission.MOTION)
    }

    private fun requestPermission(vararg permissions: Permission) {
        viewModelScope.launch {
            try {
                permissions.forEach {
                    permissionsController.providePermission(it)
                }
            } catch (deniedAlways: DeniedAlwaysException) {
                deniedAlways.localizedMessage
            } catch (denied: DeniedException) {
                denied.localizedMessage
            }
        }
    }

}