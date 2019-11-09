package com.blackstone.device.notification

import androidx.core.app.NotificationManagerCompat
import com.blackstone.device.notification.Const.NOTIFICATION_ID_FORPOST_CLOSE
import com.blackstone.device.notification.Const.NOTIFICATION_ID_FORPOST_TP
import com.blackstone.device.notification.Const.NOTIFICATION_ID_OCTAL_CLOSE
import com.blackstone.device.notification.Const.NOTIFICATION_ID_OCTAL_TP
import com.blackstone.domain.entity.Config
import com.blackstone.domain.repositories.AppRepository

class NotificationsImpl (
    private val notificationManagerCompat: NotificationManagerCompat,
    private val notificationBuilder: NotificationBuilder,
    repository: AppRepository
): Notifications {

    private var configApp: Config = repository.getSharedProviderService().getNotificationSettings()

    override fun showNotification(notificationId: Int) {
        when (notificationId) {
            NOTIFICATION_ID_FORPOST_CLOSE -> if (configApp.closeForpost) notificationManager(notificationId)
            NOTIFICATION_ID_OCTAL_CLOSE -> if (configApp.closeOctal) notificationManager(notificationId)
            NOTIFICATION_ID_FORPOST_TP -> if (configApp.tpForpost) notificationManager(notificationId)
            NOTIFICATION_ID_OCTAL_TP -> if (configApp.tpOctal) notificationManager(notificationId)
        }
    }

    private fun notificationManager(notificationId: Int) {
        notificationManagerCompat.notify(notificationId,notificationBuilder.builder(notificationId))
    }
}