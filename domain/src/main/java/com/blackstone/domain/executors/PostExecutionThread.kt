package com.blackstone.domain.executors

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler() : Scheduler
}
