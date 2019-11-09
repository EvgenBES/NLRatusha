package com.blackstone.domain.usecases.forpost

import com.blackstone.domain.repositories.AppRepository
import com.blackstone.domain.usecases.base.BaseUseCase
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 03.02.2019
 */
class UpdateForpostDataUseCase
@Inject constructor(private val repository: AppRepository) : BaseUseCase<Boolean>() {

    override suspend fun executeOnBackground(): Boolean {
        return repository.getApiService().updateDataForpost()
    }
}