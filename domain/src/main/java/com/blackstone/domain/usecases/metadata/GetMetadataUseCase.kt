package com.blackstone.domain.usecases.metadata

import androidx.lifecycle.LiveData
import com.blackstone.domain.entity.MetaInfo
import com.blackstone.domain.repositories.AppRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 07.02.2019
 */
class GetMetadataUseCase
@Inject constructor(private val repository: AppRepository) {

    fun get(): LiveData<MetaInfo> {
        return repository.getDatabaseService().getMeta()
    }
}