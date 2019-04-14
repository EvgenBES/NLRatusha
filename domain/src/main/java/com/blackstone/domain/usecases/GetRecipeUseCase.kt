package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.domain.executors.PostExecutionThread
import com.blackstone.domain.repositories.ServerRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 14.03.2019
 */
class GetRecipeUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                           private val itemRepository: ServerRepository
) : BaseUseCase(postExecutionThread) {

    fun getRecipeItem(id: Int): Flowable<List<ItemRecipeFull>> {
        return itemRepository
                .getRecept(id)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }

    fun getRecipeAlchemy(id: Int): Flowable<List<ItemRecipeFull>> {
        return itemRepository
                .getReceptAlchemy(id)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}