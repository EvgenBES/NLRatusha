package com.blackstone.domain.usecases.config

import com.blackstone.domain.entity.Config
import com.blackstone.domain.repositories.AppRepository
import com.blackstone.domain.usecases.base.BaseUseCase
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 19.05.2019
 */
class GetConfigUseCase @Inject constructor(private val repository: AppRepository) {

    fun getConfigSharedProvider(): Config {
        return repository.getSharedProviderService().getNotificationSettings()
    }
}