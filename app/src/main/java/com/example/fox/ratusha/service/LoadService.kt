package com.example.fox.ratusha.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.fox.ratusha.data.network.LoadInfoService
import java.util.concurrent.TimeUnit


class LoadService : Service() {

    val TAG = "LoadInfoService"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        someTask()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    fun someTask() {
        Thread(Runnable {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(300)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                LoadInfoService().execute()
            }
//            stopSelf()
        }).start()
    }
}