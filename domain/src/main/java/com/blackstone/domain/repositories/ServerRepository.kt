package com.blackstone.domain.repositories

interface ServerRepository {
    suspend fun updateDataForpost(): Boolean
    suspend fun updateDataOctal(): Boolean
}