package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.repositories.AppRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 09.03.2019
 */
class GetListItemCategoryUseCase
@Inject constructor(private val repository: AppRepository) :
    BaseUseCaseParams<Int, List<ItemCategory>>() {

    override suspend fun executeOnBackground(params: Int): List<ItemCategory> {
        return repository.getDatabaseService().getItemsCategory(params)
    }
}





