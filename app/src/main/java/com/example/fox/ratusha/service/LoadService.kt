package com.example.fox.ratusha.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import com.example.fox.ratusha.data.network.LoadInfoService
import java.util.concurrent.TimeUnit


class LoadService : Service() {

    val TAG = "LoadInfoService"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d(TAG, "onStartCommand")

        someTask()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "onCreate")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null


    }

    private fun someTask() {

        Thread(Runnable {
            while (true) {
                TimeUnit.SECONDS.sleep(5L)
                LoadInfoService().execute()
            }
//            stopSelf()
        }).start()
    }
}