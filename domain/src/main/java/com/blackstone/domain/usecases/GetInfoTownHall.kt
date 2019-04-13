package com.blackstone.domain.usecases

import com.blackstone.domain.entity.TownHall
import com.blackstone.domain.executors.PostExecutionThread
import com.blackstone.domain.repositories.RatushaRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 07.02.2019
 */
class GetInfoTownHall @Inject constructor(postExecutionThread: PostExecutionThread,
                                          private val itemRepository: RatushaRepository
) : BaseUseCase(postExecutionThread) {

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