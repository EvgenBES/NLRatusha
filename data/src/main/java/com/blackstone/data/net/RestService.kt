package com.blackstone.data.net

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
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
       // okHttpBuilder
       //     .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        val gson = GsonBuilder()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL_SERVER)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpBuilder.build())
            .build()

        restApi = retrofit.create(RestApi::class.java)
    }

    suspend fun getOctal(): ResponseBody {
        return restApi.getOctal().await()
    }

    suspend fun getForpost(): ResponseBody {
        return restApi.getForpost().await()
    }
}