package com.blackstone.domain.usecases

import com.blackstone.domain.executors.PostExecutionThread
import com.blackstone.domain.repositories.ServerRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 03.02.2019
 */
class UpdateDataUseCase
@Inject constructor(postExecutionThread: PostExecutionThread, private val itemRepository: ServerRepository)
    : BaseUseCase(postExecutionThread) {

    fun updateDataForpost(): Completable {
        return itemRepository.updateDataForpost()
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun updateDataOctal(): Completable {
        return itemRepository.updateDataOctal()
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}