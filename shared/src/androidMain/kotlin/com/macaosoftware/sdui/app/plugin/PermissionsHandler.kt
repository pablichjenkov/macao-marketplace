package com.macaosoftware.sdui.app.plugin

interface PermissionsHandler {
    fun onImagePermissionPressed()
    fun onVideoPermissionPressed()
    fun onNotificationPermissionPressed()
    fun onStoragePermissionPressed()
    fun onLocationPermissionPressed()
    fun onCoarseLocationPermissionPressed()
    fun onRemoteNotificationPermissionPressed()
    fun onRecordAudioPermissionPressed()
    fun onBluetoothAdvertisePermissionPressed()
    fun onMotionPermissionPressed()
}