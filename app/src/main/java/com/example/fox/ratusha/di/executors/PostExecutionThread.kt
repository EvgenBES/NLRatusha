package com.example.fox.ratusha.di.executors

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler() : Scheduler
}
