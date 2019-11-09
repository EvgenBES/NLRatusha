package com.blackstone.domain.usecases.config

import com.blackstone.domain.entity.Config
import com.blackstone.domain.repositories.AppRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 09.11.2019
 */
class UpdateConfigUseCase @Inject constructor(private val repository: AppRepository) {

    fun updateConfigSharedProvider(config: Config) {
        return repository.getSharedProviderService().updateNotificationSettings(config)
    }
}