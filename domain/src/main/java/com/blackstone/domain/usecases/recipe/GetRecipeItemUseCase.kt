package com.blackstone.domain.usecases.recipe

import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.domain.repositories.AppRepository
import com.blackstone.domain.usecases.base.BaseUseCaseParams
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 14.03.2019
 */
class GetRecipeItemUseCase
@Inject constructor(private val repository: AppRepository) :
    BaseUseCaseParams<Int, List<ItemRecipeFull>>() {

    override suspend fun executeOnBackground(params: Int): List<ItemRecipeFull> {
        return repository.getDatabaseService().getRecept(params)
    }
}