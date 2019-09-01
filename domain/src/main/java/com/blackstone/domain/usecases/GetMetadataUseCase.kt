package com.blackstone.domain.usecases

import androidx.lifecycle.LiveData
import com.blackstone.domain.entity.MetaInfo
import com.blackstone.domain.entity.TownHall
import com.blackstone.domain.repositories.DaoRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 07.02.2019
 */
class GetMetadataUseCase
    @Inject constructor(private val daoRepository: DaoRepository) {

    fun get(): LiveData<MetaInfo> {
        return daoRepository.getMeta()
    }
}