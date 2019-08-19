package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.domain.repositories.DaoRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 14.03.2019
 */
class GetRecipeItemUseCase
    @Inject constructor(private val daoRepository: DaoRepository) : BaseUseCaseParams<Int, List<ItemRecipeFull>>() {

    override suspend fun executeOnBackground(params: Int): List<ItemRecipeFull> {
        return daoRepository.getRecept(params)
    }
}