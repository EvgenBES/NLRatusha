package com.blackstone.ratusha.notif

import android.graphics.Color
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.blackstone.data.extension.notificationManager
import com.blackstone.notif.NotificationR
import com.blackstone.ratusha.R
import com.blackstone.ratusha.utils.Const.FORPOST_CLOSE
import com.blackstone.ratusha.utils.Const.FORPOST_TP
import com.blackstone.ratusha.utils.Const.OCTAL_CLOSE
import com.blackstone.ratusha.utils.Const.OCTAL_TP
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


/**
 * @author Evgeny Butov
 * @created 14.08.2019
 */
class NotificationMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        commanderNotification(remoteMessage.data["type"])
    }

    private fun commanderNotification(type: String?) {
        when (type) {
            FORPOST_CLOSE -> showNotification()
            OCTAL_CLOSE ->  showNotification()
            FORPOST_TP -> showNotification()
            OCTAL_TP -> showNotification()
            else -> {}
        }
    }



    private fun showNotification() {
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val vibrate = longArrayOf(0, 100, 200, 300)

        val builder = NotificationCompat.Builder(this, NotificationR.CHANNEL_NOTIFICATION)
            .setSmallIcon(R.drawable.ic_notif_scroll)
            .setContentTitle(this.getString(R.string.notification_fp))
            .setContentText(this.getString(R.string.notification_close_order))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setLights(Color.BLUE, 500, 500)
            .setVibrate(vibrate)
            .setSound(alarmSound)
            .setAutoCancel(true)
        this.applicationContext.notificationManager().notify(0, builder.build())
    }
}