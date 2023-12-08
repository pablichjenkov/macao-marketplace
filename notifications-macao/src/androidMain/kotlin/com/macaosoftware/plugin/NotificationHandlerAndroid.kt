package com.macaosoftware.plugin

interface NotificationHandlerAndroid {
    fun initializeNotification()
    fun showLocalNotification(title: String, body: String)
}