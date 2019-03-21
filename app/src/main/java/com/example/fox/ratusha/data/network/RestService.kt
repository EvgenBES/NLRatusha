package com.example.fox.ratusha.data.network

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Evgeny Butov
 * @created 20.03.2019
 */
class RestService {
    private val restApi: RestApi

    companion object {
        private const val URL_SERVER = "http://service.neverlands.ru/"
    }

    init {
        val okHttpBuilder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
        okHttpBuilder
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        val gson = GsonBuilder()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(URL_SERVER)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpBuilder.build())
                .build()

        restApi = retrofit.create(RestApi::class.java)
    }

    fun getOctal(): Observable<ResponseBody> {
        return restApi.getOctal()
    }

    fun getForpost(): Observable<ResponseBody> {
        return restApi.getForpost()
    }
}