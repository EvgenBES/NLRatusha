package com.blackstone.domain.repositories


interface AppRepository {
    fun getApiService(): ApiRepository
    fun getDatabaseService(): DaoRepository
}