package com.blackstone.ratusha.utils

import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author Evgeny Butov
 * @created 06.04.2019
 */
object TimerUtils {
    val observable1s: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)
    val observable5m: Observable<Long> = Observable.interval(5, TimeUnit.MINUTES)

    fun getDefTimerOrder(): String {
        return SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date().time + 864000000L) //today + 10days
    }

    /**
     * Here we create remains time - product
     * @param hour - we get hour and %2 = 0 || 1
     * @param minute - we get time in format (min:sec)
     * @param getTimeLong - we transform minute to long
     * @param fixedTimeLong - we get long 2hour
     *
     * @return result: 2 hour minus current time = time CD new products
     */
    fun timerProduct(): String {
        val hour = SimpleDateFormat("HH").format(Date()).toInt() % 2
        val minute: String = SimpleDateFormat("mm:ss", Locale.getDefault()).format(Date())
        val getTimeLong = SimpleDateFormat("mm:ss", Locale.getDefault()).parse(minute).time
        val fixedTimeLong = SimpleDateFormat("mm:ss", Locale.getDefault()).parse("59:59").time

        val result: String = SimpleDateFormat("mm:ss", Locale.getDefault()).format(fixedTimeLong - getTimeLong)

        return "0${if (hour == 0) 1 else 0}:$result"
    }


    /**
     * This method map remainder time orders
     * We gain param finish String orders and map him to remainder time orders
     * For example: we gain
     * @param finish its String "08.02.2019 03:46"
     * @return result where String finish minus real time = remainder time orders "4д 10:25:17"
     */
    fun timeMap(finish: String): String {
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

    fun updateTime(time: Long): Int {
        if (time == 0L) return 99
        val currentTime = Date().time - time
        return SimpleDateFormat("mm", Locale.getDefault()).format(currentTime).toInt()
    }
}