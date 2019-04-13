package com.blackstone.ratusha.ui.screens.forpost

import android.util.Log
import com.blackstone.domain.usecases.GetInfoTownHall
import com.blackstone.domain.usecases.GetItemForpostUseCase
import com.blackstone.ratusha.di.app.App
import com.blackstone.ratusha.ui.base.mvp.BasePresenter
import com.blackstone.ratusha.ui.base.recycler.RecyclerItemRatushaAdapter
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.ratusha.ui.screens.mainManager.MainRouter
import com.blackstone.ratusha.utils.CalculationsUtils.timeMap
import com.blackstone.ratusha.utils.CalculationsUtils.totalSum
import io.reactivex.rxkotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FForpostPresenter(view: FForpostView) : BasePresenter<MainRouter, FForpostView>(view) {

    val forpostAdapter = RecyclerItemRatushaAdapter()

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
                        onNext = {
                            forpostAdapter.setItems(it)
                            setTotalSumOrder(it)
                        },
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

    private fun setTotalSumOrder(listItem: List<ItemOrder>) {
        view.setTotalSum(totalSum(listItem))
    }

    /**
     * This thread refresh times to textview - interval 1s
     */
    //TODO change on observable RxJava
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