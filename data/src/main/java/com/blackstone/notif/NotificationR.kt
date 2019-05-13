package com.blackstone.notif

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.blackstone.data.R
import com.blackstone.data.db.AppDataBase
import com.blackstone.data.db.entity.Config
import com.blackstone.data.extension.notificationManager
import com.blackstone.domain.entity.Town.FORPOST
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 13.05.2019
 */

class NotificationR @Inject constructor(private val appDataBase: AppDataBase, private val context: Context): NotifCommander{

    private var config: Config = Config(0)

    init {
        checkTpForpost()
    }

    companion object {
        fun myNotification(mContext: Context, id: Int, title: String, description: String) {
            var builder = NotificationCompat.Builder(mContext, "ID")
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle("Simple Text")
                .setContentText("This is the simple content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            mContext.notificationManager().notify(1, builder.build())
        }
    }


    override fun getConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkTpForpost() {
       appDataBase
            .getTownHallDao()
            .getTownHall(FORPOST.getId())
            .subscribeBy(
                onNext = {
                    Log.d("AAQQ", "test 1")
                    myNotification(context, 0, "test", "test message")
                },
                onError = {
                    Log.d("AAQQ", "test 2")
                }
            )
    }

    override fun checkTpOctal() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkOrderForpost() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkOrderOctal() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}