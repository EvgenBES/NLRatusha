package com.example.fox.ratusha.data.usecases

import com.example.fox.ratusha.data.repositories.RatushaRepository
import com.example.fox.ratusha.di.executors.PostExecutionThread
import com.example.fox.ratusha.ui.entity.ItemOrder
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 08.02.2019
 */
class GetItemForpostUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                                private val itemRepository: RatushaRepository) : BaseUseCase(postExecutionThread) {

    fun getAllItemOrder(): Flowable<List<ItemOrder>> {
        return itemRepository
                .getItemForpost()
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)

    }
}