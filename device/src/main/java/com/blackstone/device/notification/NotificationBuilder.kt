package com.blackstone.device.notification

import android.app.Notification
import android.content.Context
import android.graphics.Color
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.blackstone.device.R
import com.blackstone.device.notification.Const.NOTIFICATION_CHANNEL
import com.blackstone.device.notification.Const.NOTIFICATION_ID_FORPOST_CLOSE
import com.blackstone.device.notification.Const.NOTIFICATION_ID_FORPOST_TP
import com.blackstone.device.notification.Const.NOTIFICATION_ID_OCTAL_CLOSE
import com.blackstone.device.notification.Const.NOTIFICATION_ID_OCTAL_TP
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 01.09.2019
 */
class NotificationBuilder
@Inject constructor(private val context: Context) {

    fun builder(notificationId: Int): Notification {
        return when (notificationId) {
            NOTIFICATION_ID_FORPOST_CLOSE -> createForpostCloseNotification()
            NOTIFICATION_ID_OCTAL_CLOSE -> createOctalCloseNotification()
            NOTIFICATION_ID_FORPOST_TP -> createForpostTeleportNotification()
            else -> createOctalTeleportNotification()
        }
    }

    private fun createForpostCloseNotification(): Notification{
        return build(NOTIFICATION_ID_FORPOST_CLOSE,
            R.drawable.ic_notif_banner_fp,
            context.getString(R.string.notification_fp),
            context.getString(R.string.notification_close_order))
    }

    private fun createOctalCloseNotification(): Notification{
        return build(
            NOTIFICATION_ID_OCTAL_CLOSE,
            R.drawable.ic_notif_banner_oc,
            context.getString(R.string.notification_oc),
            context.getString(R.string.notification_close_order))
    }

    private fun createForpostTeleportNotification(): Notification{
        return build(
            NOTIFICATION_ID_FORPOST_TP,
            R.drawable.ic_notif_scroll,
            context.getString(R.string.notification_fp),
            context.getString(R.string.notification_tp))
    }

    private fun createOctalTeleportNotification(): Notification{
        return build(
            NOTIFICATION_ID_OCTAL_TP,
            R.drawable.ic_notif_banner_oc,
            context.getString(R.string.notification_oc),
            context.getString(R.string.notification_tp))
    }

    private fun build(id: Int, icon: Int, title: String, description: String): Notification {
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val vibrate = longArrayOf(0, 300, 200)
        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)

        return notificationBuilder
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setLights(Color.BLUE, 500,500)
            .setVibrate(vibrate)
            .setSound(alarmSound)
            .setAutoCancel(true)
            .build()
    }
}