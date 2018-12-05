package com.example.fox.ratusha.data.network

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection

class LoadInfoService : AsyncTask<Void, Void, String>() {

    private val forpostList = arrayListOf<String>()
    private val URL_FORT = "http://service.neverlands.ru/info/cityhall_1.txt"
    private val URL_OCTAL = "http://service.neverlands.ru/info/cityhall_2.txt"

    override fun doInBackground(vararg p0: Void?): String? {

        //Load info nl service Forpost
        try {
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

//            respons(forpostList)

        } catch (e: Exception) {
            Log.d("LoadInfoService ", "Exception ${e.message}")
            e.printStackTrace()
        }


        //Load info nl service Octal
        try {
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

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    //Start after background to UI
    override fun onPreExecute() {
        super.onPreExecute()
    }

    fun respons(forpostList: ArrayList<String>) {
        val aTest = arrayListOf<String>()
        aTest.add(forpostList.get(0).split("|").toString())

    }
}