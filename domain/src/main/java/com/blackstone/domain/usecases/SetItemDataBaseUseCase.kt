package com.blackstone.domain.usecases

import com.blackstone.domain.entity.Order
import com.blackstone.domain.executors.PostExecutionThread
import com.blackstone.domain.repositories.RatushaRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 03.02.2019
 */
class SetItemDataBaseUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                                 private val itemRepository: RatushaRepository
) : BaseUseCase(postExecutionThread) {

    fun setOrder(order: List<Order>): Boolean {
        return itemRepository.updateHallTowen(order)
    }
}