package com.example.fox.ratusha.data.usecases

import com.example.fox.ratusha.di.executors.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * @author Evgeny Butov
 * @since 03.02.2019
 */

abstract class BaseUseCase(val postExecutorThread : Scheduler,
                           val workExecutorThread : Scheduler = Schedulers.io()) {

    constructor(postExecutionThread: PostExecutionThread) : this(postExecutionThread.getScheduler())

}