package com.example.fox.ratusha.data.usecases

import com.example.fox.ratusha.data.repositories.ItemRepository
import com.example.fox.ratusha.di.executors.PostExecutionThread
import com.example.fox.ratusha.ui.entity.Order
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @since 03.02.2019
 */

class ItemDataBaseUseCase @Inject constructor(
        postExecutionThread: PostExecutionThread,
        private val itemRepository: ItemRepository) : BaseUseCase(postExecutionThread) {

    fun setOrder(order: List<Order>){
        return itemRepository.setForpostItem(order)

    }
}