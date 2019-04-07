package com.example.fox.ratusha.utils

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * @author Evgeny Butov
 * @created 06.04.2019
 */
object TimerUtils {
    val observable1s: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)
    val observable5m: Observable<Long> = Observable.interval(5, TimeUnit.MINUTES)
}