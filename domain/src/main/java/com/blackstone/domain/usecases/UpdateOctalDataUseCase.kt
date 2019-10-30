package com.blackstone.domain.usecases

import com.blackstone.domain.repositories.AppRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 03.02.2019
 */
class UpdateOctalDataUseCase
@Inject constructor(private val repository: AppRepository) : BaseUseCase<Boolean>() {

    override suspend fun executeOnBackground(): Boolean {
        return repository.getApiService().updateDataOctal()
    }
}