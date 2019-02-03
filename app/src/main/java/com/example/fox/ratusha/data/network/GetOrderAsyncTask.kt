package com.example.fox.ratusha.data.network

import android.os.AsyncTask
import android.util.Log
import com.example.fox.ratusha.ui.entity.ItemOrder
import com.example.fox.ratusha.ui.entity.Order
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException
import java.nio.charset.Charset


class GetOrderAsyncTask : AsyncTask<Void, Void, List<Order>>() {

    val TAG = "LoadInfoService"

    @JvmField
    val cp1251: Charset = Charset.forName("Cp1251")

    private val urlForpost = "http://service.neverlands.ru/info/cityhall_1.txt"
    private val urlOctal = "http://service.neverlands.ru/info/cityhall_2.txt"

    override fun doInBackground(vararg p0: Void?): List<Order> {
        val result = arrayListOf<Order>()

        //Get forpost order
        val connection = URL(urlForpost).openConnection() as HttpURLConnection
        try {
            val data: String = try {
                connection.inputStream.bufferedReader(cp1251).readText()
            } catch (e: UnknownHostException) {
                " "
            }
            val listString = data.lines()
            result.add(mapOrder(listString))
        } finally {
            connection.disconnect()
        }

        //Get octal order
        val connectionOct = URL(urlOctal).openConnection() as HttpURLConnection
        try {
            val data: String = try {
                connectionOct.inputStream.bufferedReader(cp1251).readText()
            } catch (e: UnknownHostException) {
                " "
            }
            val listString = data.lines()
            result.add(mapOrder(listString))
        } finally {
            connection.disconnect()
        }

        return result
    }

    override fun onPostExecute(result: List<Order>) {
        super.onPostExecute(result)

        Log.d(TAG, "doInBackground ${result.size}")
    }


    /**
     * Map Orders
     */
    private fun mapOrder(forpostList: List<String>): Order {

        val timeList = arrayListOf<String>()
        val listItemOrder = mutableListOf<ItemOrder>()
        val productList = arrayListOf<String>()

        var i = 0
        while (i < forpostList.size) {
            if (i == 0) {
                val timeOrder = forpostList[0].split("|")
                for (list in timeOrder) {
                    timeList.add(list)
                }
            }
            if (i == 1) {
                val replaceText = forpostList[1].replace("order|", "").replace("item,", "")
                val itemOrder = replaceText.split("@")
                for (list in itemOrder) {
                    val tempList = list.split(",")
                    if (tempList.size > 4)
                        listItemOrder.add(ItemOrder(tempList[0], tempList[1], tempList[2], tempList[3], tempList[4]))
                }
            }
            if (i == 2) {
                val replaceText = forpostList[2].replace("store|item|", "").replace("|1", "")
                val itemProduct = replaceText.split("|")
                for (tempProduct in itemProduct) {
                    productList.add(tempProduct)
                }
            }
            i++
        }

        if (timeList.size == 3 && productList.size == 2) {
            return Order(timeList[1], timeList[2], listItemOrder, productList[0], productList[1])
        }

        return Order(listItem = listItemOrder)
    }


}