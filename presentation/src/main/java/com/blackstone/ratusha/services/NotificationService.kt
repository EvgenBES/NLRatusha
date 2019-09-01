package com.blackstone.ratusha.services

import com.blackstone.device.notification.Const.NOTIFICATION_FORPOST_CLOSE
import com.blackstone.device.notification.Const.NOTIFICATION_FORPOST_TP
import com.blackstone.device.notification.Const.NOTIFICATION_ID_FORPOST_CLOSE
import com.blackstone.device.notification.Const.NOTIFICATION_ID_FORPOST_TP
import com.blackstone.device.notification.Const.NOTIFICATION_ID_OCTAL_CLOSE
import com.blackstone.device.notification.Const.NOTIFICATION_ID_OCTAL_TP
import com.blackstone.device.notification.Const.NOTIFICATION_OCTAL_CLOSE
import com.blackstone.device.notification.Const.NOTIFICATION_OCTAL_TP
import com.blackstone.device.notification.Notifications
import com.blackstone.ratusha.app.App
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import javax.inject.Inject


/**
 * @author Evgeny Butov
 * @created 14.08.2019
 */
class NotificationService : FirebaseMessagingService() {

    @Inject lateinit var notifications: Notifications

    init {
        App.appComponent.runInject(this)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        commanderNotification(remoteMessage.data["type"])
    }

    private fun commanderNotification(type: String?) {
        when (type) {
            NOTIFICATION_FORPOST_CLOSE -> notifications.showNotification(NOTIFICATION_ID_FORPOST_CLOSE)
            NOTIFICATION_OCTAL_CLOSE -> notifications.showNotification(NOTIFICATION_ID_OCTAL_CLOSE)
            NOTIFICATION_FORPOST_TP -> notifications.showNotification(NOTIFICATION_ID_FORPOST_TP)
            NOTIFICATION_OCTAL_TP -> notifications.showNotification(NOTIFICATION_ID_OCTAL_TP)
            else -> {}
        }
    }
}