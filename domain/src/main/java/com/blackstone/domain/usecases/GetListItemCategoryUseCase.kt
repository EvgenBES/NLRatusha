package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.extension.convertToLinkedList
import com.blackstone.domain.repositories.DaoRepository
import java.util.*
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 09.03.2019
 */
class GetListItemCategoryUseCase
    @Inject constructor(private val daoRepository: DaoRepository) : BaseUseCaseParams<Int, LinkedList<ItemCategory>>() {

    override suspend fun executeOnBackground(params: Int): LinkedList<ItemCategory> {
        return daoRepository.getItemsCategory(params).convertToLinkedList()
    }
}





