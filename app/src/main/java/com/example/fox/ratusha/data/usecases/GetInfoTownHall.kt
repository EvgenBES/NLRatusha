package com.example.fox.ratusha.data.usecases

import com.example.fox.ratusha.data.repositories.ItemRepository
import com.example.fox.ratusha.di.executors.PostExecutionThread
import com.example.fox.ratusha.ui.entity.TownHall
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 07.02.2019
 */
class GetInfoTownHall @Inject constructor(postExecutionThread: PostExecutionThread,
                                          private val itemRepository: ItemRepository) : BaseUseCase(postExecutionThread) {

    fun get(): Flowable<List<TownHall>> {
        return itemRepository
                .getInfoTownHall()
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }

    fun getTownHall(id: Int): Flowable<TownHall> {
        return itemRepository
                .getTownHall(id)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}