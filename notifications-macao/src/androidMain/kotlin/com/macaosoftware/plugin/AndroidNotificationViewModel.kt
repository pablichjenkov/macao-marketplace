package com.macaosoftware.plugin

import androidx.lifecycle.ViewModel
import com.macaosoftware.plugin.notifications.macao.R
import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration

class AndroidNotificationViewModel() : ViewModel(), NotificationHandlerAndroid {
    init {
        initializeNotification()
    }


    override fun initializeNotification() {
        NotifierManager.initialize(
            configuration = if (isAndroid()) {
                NotificationPlatformConfiguration.Android(
                    notificationIconResId = R.drawable.baseline_notifications_active_24
                )
            } else {
                // iOS specific configuration if needed
                NotificationPlatformConfiguration.Ios
            }
        )
    }


    override fun showLocalNotification(title: String, body: String) {
        val notifier = NotifierManager.getLocalNotifier()
        notifier.notify(title, body)
    }

    private fun isAndroid(): Boolean {
        // Your Android-specific check goes here
        return true // Replace with your actual Android check
    }
}
