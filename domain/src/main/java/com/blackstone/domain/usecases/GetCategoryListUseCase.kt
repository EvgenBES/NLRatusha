package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.extension.convertToLinkedList
import com.blackstone.domain.repositories.DaoRepository
import java.util.*
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 08.03.2019
 */
class GetCategoryListUseCase
    @Inject constructor( private val daoRepository: DaoRepository)
    : BaseUseCase<LinkedList<ItemCategory>>() {

    override suspend fun executeOnBackground(): LinkedList<ItemCategory> {
        return daoRepository.getCategoryList().convertToLinkedList()
    }
}