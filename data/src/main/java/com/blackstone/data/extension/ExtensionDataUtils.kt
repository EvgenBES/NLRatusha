package com.blackstone.data.extension

import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.entity.Order
import com.blackstone.domain.entity.TownHall
import java.lang.NumberFormatException

/**
 * @author Evgeny Butov
 * @created 13.04.2019
 */

fun String.mapResponseOrder(): Order {
    val listString = this.lines()

    val timeList = arrayListOf<String>()
    val listItemOrder = mutableListOf<ItemOrder>()
    val productList = arrayListOf<String>()

    var i = 0
    while (i < listString.size) {
        if (i == 0) {
            val timeOrder = listString[0].split("|")
            for (list in timeOrder) {
                timeList.add(list)
            }
        }
        if (i == 1) {
            val replaceText = listString[1].replace("order|", "").replace("item,", "")
            val itemOrder = replaceText.split("@")
            for (list in itemOrder) {
                val tempList = list.split(",")
                if (tempList.size > 4)
                    try {
                        listItemOrder.add(ItemOrder(tempList[0].toInt(), tempList[1], tempList[2], tempList[3].toInt(), tempList[4].toInt()))
                    } catch (e: NumberFormatException) {
                        Log.e(TAG, "mapOrder NumberFormatException")
                    }
            }
        }
        if (i == 2) {
            val replaceText = listString[2].replace("store|item|", "")
            val itemProduct = replaceText.split("|")
            for (tempProduct in itemProduct) {
                productList.add(tempProduct)
            }
        }
        i++
    }

    if (timeList.size == 3 && productList.size > 1) {
        return Order(listItemOrder, TownHall(timeList[0].toInt(), timeList[1], timeList[2], productList[1]))
    }

    return Order(itemList = listItemOrder, townHall = TownHall())
}