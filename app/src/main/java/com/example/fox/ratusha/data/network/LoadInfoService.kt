package com.example.fox.ratusha.data.network

import android.os.AsyncTask
import android.util.Log
import com.example.fox.ratusha.ui.entity.Order
import com.example.fox.ratusha.ui.main.MainActivity
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection

class LoadInfoService : AsyncTask<Void, Void, String>() {

    private var forpOrder: Order? = null
    private var octalOrder: Order? = null
    private val URL_FORT = "http://service.neverlands.ru/info/cityhall_1.txt"
    private val URL_OCTAL = "http://service.neverlands.ru/info/cityhall_2.txt"

    override fun doInBackground(vararg p0: Void?): String? {

        //Load info nl service Forpost
        try {
            val forpostList = arrayListOf<String>()
            val conn: URLConnection = URL(URL_FORT).openConnection()
            conn.connect()
            val intpStream: InputStream = conn.getInputStream()
            val reader = BufferedReader(InputStreamReader(intpStream, "Cp1251"), 8)
            var line: String? = null
            while ({ line = reader.readLine(); line }() != null) {

                line?.let {
                    if (it.contains("order") && it.length < 10) {
                        // order forpost finished
                    }
                    if (it.contains("Телепорт")) {
                        // appeared teleport in ratusha forpost
                    }
                    forpostList.add(it)
//                    Log.d("LoadInfoService", it)
                }
            }
            intpStream.close()

            forpOrder = respons(forpostList)

        } catch (e: Exception) {
            Log.d("LoadInfoService ", "Exception ${e.message}")
            e.printStackTrace()
        }


        //Load info nl service Octal
        try {
            val octalList = arrayListOf<String>()
            val conn: URLConnection = URL(URL_OCTAL).openConnection()
            conn.connect()
            val intpStream: InputStream = conn.getInputStream()
            val reader = BufferedReader(InputStreamReader(intpStream, "Cp1251"), 8)
            var line: String? = null
            while ({ line = reader.readLine(); line }() != null) {
                line?.let {
                    if (it.contains("order") && it.length < 10) {
                        // order octal finished
                    }
                    if (it.contains("Телепорт")) {
                        // appeared teleport in ratusha octal
                    }
//                    Log.d("LoadInfoService", it)
                }
            }
            intpStream.close()

            octalOrder = respons(octalList)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    //Start after background to UI
    override fun onPreExecute() {
        super.onPreExecute()
    }

    fun respons(forpostList: ArrayList<String>): Order {
        val responseOrder = Order(forpostList,forpostList,forpostList)
        var i = 0
        while (i < forpostList.size) {
            if (i == 0) {
                responseOrder.dataOrder = forpostList[0].split("|")
            }
            if (i == 1) {
                val replaceText = forpostList[1].replace("order|", "")
                responseOrder.itemOrder = replaceText.split("@")
            }
            if (i == 2) {
                val replaceText = forpostList[2].replace("store|item|", "")
                responseOrder.storeOrder = replaceText.split("|")
            }
            i++
        }
        return responseOrder
    }
}