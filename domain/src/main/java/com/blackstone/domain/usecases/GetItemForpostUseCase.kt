package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.executors.PostExecutionThread
import com.blackstone.domain.repositories.DaoRepository
import com.blackstone.domain.usecases.base.RxUseCase
import com.blackstone.domain.utils.DomainUtils.sortItemOrder
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 08.02.2019
 */
class GetItemForpostUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                                private val daoRepository: DaoRepository
) : RxUseCase(postExecutionThread) {

    fun getAllItemOrder(): Flowable<List<ItemOrder>> {
        return daoRepository
            .getItemForpost()
            .map { sortItemOrder(it) }
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)

    }
}