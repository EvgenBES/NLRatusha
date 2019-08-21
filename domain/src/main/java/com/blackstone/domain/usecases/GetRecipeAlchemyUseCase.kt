package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.domain.extension.convertToLinkedList
import com.blackstone.domain.repositories.DaoRepository
import java.util.*
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 14.03.2019
 */
class GetRecipeAlchemyUseCase
    @Inject constructor(private val daoRepository: DaoRepository) : BaseUseCaseParams<Int, LinkedList<ItemRecipeFull>>() {


    override suspend fun executeOnBackground(params: Int): LinkedList<ItemRecipeFull> {
        return daoRepository.getReceptAlchemy(params).convertToLinkedList()
    }
}