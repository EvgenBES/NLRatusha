package com.blackstone.domain.usecases

import com.blackstone.domain.repositories.ServerRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 03.02.2019
 */
class UpdateOctalDataUseCase
@Inject constructor(private val itemRepository: ServerRepository)
    : BaseUseCase<Boolean>() {

    override suspend fun executeOnBackground(): Boolean {
        return itemRepository.updateDataOctal()
    }
}