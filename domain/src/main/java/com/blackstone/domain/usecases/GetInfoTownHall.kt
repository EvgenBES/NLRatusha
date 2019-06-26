package com.blackstone.domain.usecases

import com.blackstone.domain.entity.TownHall
import com.blackstone.domain.executors.PostExecutionThread
import com.blackstone.domain.repositories.DaoRepository
import com.blackstone.domain.usecases.base.RxUseCase
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 07.02.2019
 */
class GetInfoTownHall @Inject constructor(postExecutionThread: PostExecutionThread,
                                          private val daoRepository: DaoRepository
) : RxUseCase(postExecutionThread) {

    fun get(): Flowable<List<TownHall>> {
        return daoRepository
            .getInfoTownHall()
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun getTownHall(id: Int): Flowable<TownHall> {
        return daoRepository
            .getTownHall(id)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}