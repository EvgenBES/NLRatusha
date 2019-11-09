package com.blackstone.domain.usecases.category

import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.repositories.AppRepository
import com.blackstone.domain.usecases.base.BaseUseCaseParams
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





