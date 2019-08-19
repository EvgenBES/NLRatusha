package com.blackstone.domain.usecases

import androidx.lifecycle.LiveData
import com.blackstone.domain.entity.Config
import com.blackstone.domain.repositories.DaoRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 19.05.2019
 */
class GetConfigUseCase
    @Inject constructor(private val itemRepository: DaoRepository): BaseUseCaseParams<Config, Boolean>() {

    fun getConfig(): LiveData<Config> {
        return itemRepository.getConfig()
    }

    override suspend fun executeOnBackground(params: Config): Boolean {
        itemRepository.setConfig(params)
        return true
    }

}