package com.blackstone.domain.usecases.category

import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.repositories.AppRepository
import com.blackstone.domain.usecases.base.BaseUseCase
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 08.03.2019
 */
class GetCategoryListUseCase
@Inject constructor(private val repository: AppRepository) :
    BaseUseCase<List<ItemCategory>>() {

    override suspend fun executeOnBackground(): List<ItemCategory> {
        return repository.getDatabaseService().getCategoryList()
    }
}