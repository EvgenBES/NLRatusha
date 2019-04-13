package com.blackstone.domain.usecases

import com.blackstone.domain.executors.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * @author Evgeny Butov
 * @created 03.02.2019
 */

abstract class BaseUseCase(val postExecutorThread : Scheduler,
                           val workExecutorThread : Scheduler = Schedulers.io()) {

    constructor(postExecutionThread: PostExecutionThread) : this(postExecutionThread.getScheduler())

}