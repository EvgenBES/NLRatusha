package com.example.fox.ratusha.data.usecases

import com.example.fox.ratusha.data.repositories.ItemRepository
import com.example.fox.ratusha.di.executors.PostExecutionThread
import com.example.fox.ratusha.ui.entity.ItemOrder
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @since 08.02.2019
 */
class GetItemOctalUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                              private val itemRepository: ItemRepository) : BaseUseCase(postExecutionThread) {

    fun getAllItemOrder(): Flowable<List<ItemOrder>> {
        return itemRepository
                .getItemOctal()
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}