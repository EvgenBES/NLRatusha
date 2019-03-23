package com.example.fox.ratusha.data.usecases

import com.example.fox.ratusha.data.repositories.RatushaRepository
import com.example.fox.ratusha.di.executors.PostExecutionThread
import com.example.fox.ratusha.ui.entity.Order
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 03.02.2019
 */
class SetItemDataBaseUseCase @Inject constructor(postExecutionThread: PostExecutionThread,
                                                 private val itemRepository: RatushaRepository) : BaseUseCase(postExecutionThread) {

    fun setOrder(order: List<Order>): Boolean {
        return itemRepository.updateHallTowen(order)
    }
}