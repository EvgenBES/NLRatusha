package com.example.fox.ratusha.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.fox.ratusha.network.LoadInfoService
import java.util.concurrent.TimeUnit


class LoadService : Service() {

    val TAG = "LoadInfoService"

    override fun onCreate() {
        Log.d(TAG, " Start onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, " Start onStartCommand")
        someTask()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, " Start onDestroy")
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG, " Start onBind")
        return null
    }


    fun someTask() {
        Thread(Runnable {
            for (i in 1..5) {
                Log.d(TAG, "i = $i")
                LoadInfoService().execute()
                try {
                    TimeUnit.SECONDS.sleep(5)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            stopSelf()
        }).start()
    }
}