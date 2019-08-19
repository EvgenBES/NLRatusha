package com.blackstone.notif

import android.content.Context
import android.graphics.Color
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.blackstone.data.db.AppDataBase
import com.blackstone.data.db.entity.ConfigApp
import com.blackstone.data.db.entity.InfoTownHall
import com.blackstone.data.extension.notificationManager
import java.util.*
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 13.05.2019
 */

class NotificationR @Inject constructor(private val appDataBase: AppDataBase, private val context: Context) :
    NotifCommander {

    private var config: ConfigApp = ConfigApp(0)
    private var timeTpFp: Long = 0
    private var timeTpOc: Long = 0
    private var timeOrderFp: Long = 0
    private var timeOrderOc: Long = 0
    private var sumItemListFp = -1
    private var sumItemListOctal = -1

    companion object {
        const val ID_TP_FORPOST = 1
        const val ID_TP_OCTAL = 2
        const val ID_ORDER_FORPOST = 3
        const val ID_ORDER_OCTAL = 4
        const val CHANNEL_NOTIFICATION = "NLR_NOTIFICATION"
        const val URL_TP = "i_w28_33"
        const val TIME_40M: Long =  40 * 60 * 1000
    }

    init {
      //  getConfig()
      //  checkTpForpost()
      //  checkTpOctal()
      //  checkOrderForpost()
      //  checkOrderOctal()
    }

    private fun showNotification(mContext: Context, id: Int, icon: Int, title: String, description: String) {
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val vibrate = longArrayOf(0, 100, 200, 300)

        val builder = NotificationCompat.Builder(mContext, CHANNEL_NOTIFICATION)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setLights(Color.BLUE, 500,500)
            .setVibrate(vibrate)
            .setSound(alarmSound)
            .setAutoCancel(true)
        mContext.notificationManager().notify(id, builder.build())
    }

//
//
//
//    override fun checkTpForpost() {
//        appDataBase
//            .getTownHallDao()
//            .getTownHall(FORPOST.getId())
//            .subscribeBy(
//                onNext = {
//                    if (config.tpForpost)
//                        if (checkTp(it) && timeTpFp < getTime()) {
//                            showNotification(
//                                context,
//                                ID_TP_OCTAL,
//                                R.drawable.ic_notif_scroll,
//                                context.getString(R.string.notification_fp),
//                                context.getString(R.string.notification_tp)
//                            )
//                            timeTpFp = getTime() + TIME_40M
//                        }
//                },
//                onError = {
//                    Log.d("AAQQ", "test 2")
//                }
//            )
//    }
//
//    override fun checkTpOctal() {
//        appDataBase
//            .getTownHallDao()
//            .getTownHall(OCTAL.getId())
//            .subscribeBy(
//                onNext = {
//                    if (config.tpOctal)
//                        if (checkTp(it) && timeTpOc < getTime()) {
//                            showNotification(
//                                context,
//                                ID_TP_FORPOST,
//                                R.drawable.ic_notif_scroll,
//                                context.getString(R.string.notification_oc),
//                                context.getString(R.string.notification_tp)
//                            )
//                            timeTpOc = getTime() + TIME_40M
//                        }
//                },
//                onError = {
//                    Log.d("AAQQ", "test 3")
//                }
//            )
//    }


  /**
  * override fun checkOrderForpost() {
  *     appDataBase
  *         .getForpDao()
  *         .getAll()
  *         .subscribeBy(
  *             onNext = {
  *                 if (it.size == 1 && it[0].id == 0) {
  *                     showNotification(
  *                         context,
  *                         ID_ORDER_FORPOST,
  *                         R.drawable.ic_notif_banner_fp,
  *                         context.getString(R.string.notification_fp),
  *                         context.getString(R.string.notification_close_order)
  *                     )
  *                 }
  *             },
  *             onError = {}
  *         )
  * }
*/
 // override fun checkOrderOctal() {
 //     appDataBase
 //         .getOctDao()
 //         .getAll()
 //         .subscribeBy(
 //             onNext = {
 //                 if (it.size == 1 && it[0].id == 0) {
 //                     showNotification(
 //                         context,
 //                         ID_ORDER_OCTAL,
 //                         R.drawable.ic_notif_banner_fp,
 //                         context.getString(R.string.notification_oc),
 //                         context.getString(R.string.notification_close_order)
 //                     )
 //                 }
 //             },
 //             onError = {}
 //         )
 // }

    private fun checkTp(it: InfoTownHall): Boolean = (it.url.contains(URL_TP))
    private fun getTime(): Long = Date().time

}