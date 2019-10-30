package com.blackstone.domain.usecases

import androidx.lifecycle.LiveData
import com.blackstone.domain.entity.Config
import com.blackstone.domain.repositories.AppRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 19.05.2019
 */
class GetConfigUseCase
@Inject constructor(private val repository: AppRepository) :
    BaseUseCaseParams<Config, Boolean>() {

    fun getConfig(): LiveData<Config> {
        return repository.getDatabaseService().getConfig()
    }

    override suspend fun executeOnBackground(params: Config): Boolean {
        repository.getDatabaseService().setConfig(params)
        return true
    }
}