package com.example.fox.ratusha.data.usecases

import com.example.fox.ratusha.data.repositories.RatushaRepository
import com.example.fox.ratusha.di.executors.PostExecutionThread
import com.example.fox.ratusha.ui.entity.ItemRecipe
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 14.03.2019
 */
class GetRecipeUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                           private val itemRepository: RatushaRepository) : BaseUseCase(postExecutionThread) {

    fun getRecipeOrder(id: Int): Flowable<List<ItemRecipe>> {
        return itemRepository
                .getRecept(id)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}