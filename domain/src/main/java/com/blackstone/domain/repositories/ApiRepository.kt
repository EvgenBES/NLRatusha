package com.blackstone.domain.repositories

interface ApiRepository {
    suspend fun updateDataForpost(): Boolean
    suspend fun updateDataOctal(): Boolean
}