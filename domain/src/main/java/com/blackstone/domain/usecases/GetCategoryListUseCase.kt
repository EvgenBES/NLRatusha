package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.repositories.AppRepository
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