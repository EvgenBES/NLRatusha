package com.blackstone.device.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Intent


/**
 * @author Evgeny Butov
 * @created 01.09.2019
 */
class NotificationBuilder
@Inject constructor(private val context: Context,
                    private val intentActivity: Intent) {

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
            createBitmapIcon(R.drawable.ic_notif_banner_fp),
            context.getString(R.string.notification_fp),
            context.getString(R.string.notification_close_order))
    }

    private fun createOctalCloseNotification(): Notification{
        return build(
            NOTIFICATION_ID_OCTAL_CLOSE,
            createBitmapIcon(R.drawable.ic_notif_banner_oc),
            context.getString(R.string.notification_oc),
            context.getString(R.string.notification_close_order))
    }

    private fun createForpostTeleportNotification(): Notification{
        return build(
            NOTIFICATION_ID_FORPOST_TP,
            createBitmapIcon(R.drawable.ic_notif_scroll),
            context.getString(R.string.notification_fp),
            context.getString(R.string.notification_tp))
    }

    private fun createOctalTeleportNotification(): Notification{
        return build(
            NOTIFICATION_ID_OCTAL_TP,
            createBitmapIcon(R.drawable.ic_notif_scroll),
            context.getString(R.string.notification_oc),
            context.getString(R.string.notification_tp))
    }

    private fun build(id: Int, largeIcon: Bitmap, title: String, description: String)
            : Notification {

        //Configuration
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val vibrate = longArrayOf(0, 300, 200)
        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
        val resultPendingIntent = PendingIntent.getActivity(
            context, 0, intentActivity, FLAG_UPDATE_CURRENT
        )

        return notificationBuilder
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(description)
            .setLargeIcon(largeIcon)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setContentIntent(resultPendingIntent)
            .setVibrate(vibrate)
            .setSound(alarmSound)
            .setAutoCancel(true)
            .build()
    }

    private fun createBitmapIcon(icon: Int): Bitmap {
        return BitmapFactory.decodeResource(context.resources, icon)
    }
}