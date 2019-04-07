package com.example.fox.ratusha.data.usecases

import com.example.fox.ratusha.data.repositories.RatushaRepository
import com.example.fox.ratusha.di.executors.PostExecutionThread
import com.example.fox.ratusha.ui.entity.ItemRecipeFull
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 14.03.2019
 */
class GetRecipeUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                           private val itemRepository: RatushaRepository) : BaseUseCase(postExecutionThread) {

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