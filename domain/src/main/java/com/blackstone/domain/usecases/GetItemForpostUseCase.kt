package com.blackstone.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.extension.convertToLinkedList
import com.blackstone.domain.repositories.DaoRepository
import com.blackstone.domain.utils.DomainUtils.sortItemOrder
import java.util.*
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 08.02.2019
 */
class GetItemForpostUseCase
    @Inject constructor(private val daoRepository: DaoRepository) {

    fun getAllItemOrder(): LiveData<LinkedList<ItemOrder>> {
        return Transformations.map(daoRepository.getItemForpost()) { sortItemOrder(it).convertToLinkedList() }

    }
}