package com.example.fox.ratusha.utils

import com.example.fox.ratusha.ui.entity.ItemOrder
import com.example.fox.ratusha.ui.entity.TotalSum
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object CalculationsUtils {

    private val formatText: DecimalFormat = DecimalFormat("#,###")

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

    fun totalSum(listItem: List<ItemOrder>): TotalSum {
        var sum = 0
        var paid = 0

        listItem.forEach {
            sum += it.price * it.countFinish
            paid += it.price * it.countStart
        }

        return TotalSum(total = transformTotalSum(sum), paid = transformTotalSum(paid), paidPercent = calculatePercent(sum, paid),
                remainder = transformTotalSum(sum - paid), remainderPercent = (100 - calculatePercent(sum, paid).toInt()).toString())
    }

    fun calculatePercent(sum: Int, paid: Int): String {
        return if (sum != 0) {
            (paid / (sum / 100)).toString()
        } else {
            "100"
        }
    }

    fun transformTotalSum(intText: Int): String {
        return formatText.format(intText)
    }

    fun totalRemainderCardView(price: Int, countStart: Int, countFinish: Int): Boolean {
       return ((price * countFinish) - (price * countStart)) != 0
    }

}