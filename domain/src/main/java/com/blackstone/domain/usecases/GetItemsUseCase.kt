package com.blackstone.domain.usecases

import com.blackstone.domain.entity.Item
import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.executors.PostExecutionThread
import com.blackstone.domain.repositories.ServerRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 09.03.2019
 */
class GetItemsUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                          private val itemRepository: ServerRepository
) : BaseUseCase(postExecutionThread) {

    fun getCategoryListOrder(id: Int): Flowable<List<ItemCategory>> {
        return itemRepository
                .getItemsCategory(id)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }

    fun getItem(id: Int): Flowable<Item> {
        return itemRepository
                .getItem(id)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}