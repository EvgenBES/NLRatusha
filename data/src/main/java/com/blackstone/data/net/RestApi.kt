package com.blackstone.data.net

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET

/**
 * @author Evgeny Butov
 * @created 20.03.2019
 */
interface RestApi {

    @GET("info/cityhall_1.txt")
    suspend fun getForpostAsync(): Deferred<ResponseBody>

    @GET("info/cityhall_2.txt")
    suspend fun getOctalAsync(): Deferred<ResponseBody>

}