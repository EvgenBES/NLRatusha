package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.executors.PostExecutionThread
import com.blackstone.domain.repositories.ServerRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 08.02.2019
 */
class GetItemForpostUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                                private val itemRepository: ServerRepository
) : BaseUseCase(postExecutionThread) {

    fun getAllItemOrder(): Flowable<List<ItemOrder>> {
        return itemRepository
                .getItemForpost()
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)

    }
}