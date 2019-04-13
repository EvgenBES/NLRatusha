package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.executors.PostExecutionThread
import com.blackstone.domain.repositories.RatushaRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 08.03.2019
 */
class GetCategoryListUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                                 private val itemRepository: RatushaRepository
) : BaseUseCase(postExecutionThread) {

    fun getCategoryListOrder(): Flowable<List<ItemCategory>> {
        return itemRepository
                .getCategoryList()
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}