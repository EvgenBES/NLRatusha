package com.blackstone.domain.extension

import com.blackstone.domain.entity.ErrorModel
import com.blackstone.domain.entity.ErrorStatus
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Exception.mapToDomainErrorException(throwable: Throwable?): ErrorModel {
    val errorModel: ErrorModel? = when (throwable) {

        // handle api call timeout error
        is SocketTimeoutException -> {
            ErrorModel("TIME OUT...",0, ErrorStatus.TIMEOUT)
        }

        // handle connection error
        is IOException -> {
            ErrorModel("CHECK CONNECTION",0, ErrorStatus.NO_CONNECTION)
        }

        is UnknownHostException -> {
            ErrorModel("CHECK CONNECTION",0, ErrorStatus.NO_CONNECTION)
        }
        else -> null
    }
    return errorModel!!
}