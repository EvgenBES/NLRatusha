package com.blackstone.ratusha.utils

import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.domain.entity.TotalSum
import com.blackstone.domain.entity.TypeRecipe
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object CalculationsUtils {

    fun totalSum(listItem: List<ItemOrder>): TotalSum {
        var sum = 0
        var paid = 0

        listItem.forEach {
            sum += it.price * it.countFinish
            paid += it.price * it.countStart
        }

        return TotalSum(
            total = transformTotalSum(sum),
            paid = transformTotalSum(paid),
            remainder = transformTotalSum(sum - paid)
        )
    }


    fun calculatePercent(sum: Int, paid: Int): Int {
        return if (sum != 0) {
            (paid / (sum / 100))
        } else {
            100
        }
    }


    fun transformTotalSum(intText: Int): String {
        val formatText: DecimalFormat = DecimalFormat("#,###")
        return formatText.format(intText)
    }


    fun totalRemainderCardView(price: Int, countStart: Int, countFinish: Int): Boolean {
        return ((price * countFinish) - (price * countStart)) != 0
    }


    fun getTotalPriceRecipte(listItem: List<ItemRecipeFull>): String {
        val decimalFormat: DecimalFormat = DecimalFormat("#.##")
        var total = 0.0

        listItem.forEach {
            if (it.typeRecipe == TypeRecipe.DEFAULT) total += it.number * it.price else total += it.price
        }

        return decimalFormat.format(total)
    }


    fun countProgress(listItem: List<ItemOrder>): String {
        var sumStartCount = 0.0
        var sumFinishCount = 0.0

        for (item in listItem) {
            sumStartCount += item.countStart
            sumFinishCount += item.countFinish
        }

        val result = (sumStartCount / (sumFinishCount / 100))

        return "${result.toInt()}" // return 0..99%
    }

}