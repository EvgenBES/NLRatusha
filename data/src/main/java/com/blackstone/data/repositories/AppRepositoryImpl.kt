package com.blackstone.data.repositories

import com.blackstone.domain.repositories.SharedProvider
import com.blackstone.domain.repositories.ApiRepository
import com.blackstone.domain.repositories.DaoRepository
import com.blackstone.domain.repositories.AppRepository
import javax.inject.Inject

class AppRepositoryImpl  @Inject constructor(
    private val apiRepository: ApiRepository,
    private val daoRepository: DaoRepository,
    private val sharedProvider: SharedProvider
) : AppRepository {

    override fun getDatabaseService(): DaoRepository = daoRepository

    override fun getApiService(): ApiRepository = apiRepository

    override fun getSharedProviderService(): SharedProvider = sharedProvider
}