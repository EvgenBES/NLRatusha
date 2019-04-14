package com.blackstone.domain.usecases

import com.blackstone.domain.executors.PostExecutionThread
import com.blackstone.domain.repositories.ServerRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 03.02.2019
 */
class UpdateDataUseCase
@Inject constructor(postExecutionThread: PostExecutionThread, private val itemRepository: ServerRepository)
    : BaseUseCase(postExecutionThread) {

    fun updateData(): Boolean {
        return itemRepository.updateData()
    }
}