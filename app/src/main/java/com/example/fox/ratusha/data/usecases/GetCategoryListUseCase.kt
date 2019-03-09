package com.example.fox.ratusha.data.usecases

import com.example.fox.ratusha.data.db.entity.Category
import com.example.fox.ratusha.data.repositories.RatushaRepository
import com.example.fox.ratusha.di.executors.PostExecutionThread
import com.example.fox.ratusha.ui.entity.ItemCategory
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 08.03.2019
 */
class GetCategoryListUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                                 private val itemRepository: RatushaRepository) : BaseUseCase(postExecutionThread) {

    fun getCategoryListOrder(): Flowable<List<ItemCategory>> {
        return itemRepository
                .getCategoryList()
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}