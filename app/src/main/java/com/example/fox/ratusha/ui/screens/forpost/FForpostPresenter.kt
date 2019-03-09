package com.example.fox.ratusha.ui.screens.forpost

import android.util.Log
import com.example.fox.ratusha.data.usecases.GetInfoTownHall
import com.example.fox.ratusha.data.usecases.GetItemForpostUseCase
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import com.example.fox.ratusha.ui.base.recycler.BaseRecyclerCardAdapter
import com.example.fox.ratusha.ui.screens.mainManager.MainRouter
import io.reactivex.rxkotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FForpostPresenter(view: FForpostView) : BasePresenter<MainRouter, FForpostView>(view) {

    val forpostAdapter = BaseRecyclerCardAdapter()

    private lateinit var timerThread: Thread
    private var remainderTimeOrderForpost = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date().time + 864000000L) //today + 10days

    @Inject
    lateinit var getItemForpost: GetItemForpostUseCase

    @Inject
    lateinit var getInfoTownHall: GetInfoTownHall

    init {
        App.appComponent.runInject(this)

        getItem()
        getTownHall()
    }

    override fun onResume() {
        super.onResume()
        timerThread = createdTimerThread()
        startTimerThread()
    }

    override fun onPause() {
        super.onPause()
        stopTimerThread()
    }

    private fun startTimerThread() {
        if (!timerThread.isAlive) timerThread.start()
    }

    private fun stopTimerThread() {
        timerThread.interrupt()
    }


    private fun getItem() {
        val disposable = getItemForpost.getAllItemOrder()
                .subscribeBy(
                        onNext = { forpostAdapter.setItems(it) },
                        onError = { Log.d("AAQQ", "Error message: ${it.message}") }
                )
        addToDisposible(disposable)
    }


    /**
     * Get remainder time order from database
     */
    private fun getTownHall() {
        val disposable = getInfoTownHall.getTownHall(1).subscribeBy(
                onNext = { remainderTimeOrderForpost = it.finish },
                onError = { Log.d("AAQQ", "getTownHall message: ${it.message}") }
        )
        addToDisposible(disposable)
    }


    private fun setTimeOrder() {
        view.setTimerOrder(timeMap(remainderTimeOrderForpost))
    }

    private fun timeMap(finish: String): String {
        return if (finish != " ") {
            val fixTime = 10800000L // 3 hor
            val dateFinish = SimpleDateFormat("dd.MM.yyyy HH:mm").parse(finish).time
            val dateNew = dateFinish - Date().time - fixTime

            val daysLeft = SimpleDateFormat("d", Locale.getDefault()).format(dateNew).toInt()
            val result = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(dateNew)

            "${daysLeft - 1}д $result"
        } else {
            "0д 0:00:00"
        }
    }

    /**
     * This thread refresh times to textview - interval 1s
     */
    private fun createdTimerThread(): Thread {
        return object : Thread() {
            override fun run() {
                try {
                    while (!isInterrupted) {
                        router?.activity?.runOnUiThread {
                            setTimeOrder()
                        }
                        sleep(1000) //1s
                    }
                } catch (e: InterruptedException) {
                    Log.e("FForpostPresenter", "InterruptedException ${e.message}")
                }
            }
        }
    }




}