package com.blackstone.ratusha.di.executors


import com.blackstone.domain.executors.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutionThread {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}