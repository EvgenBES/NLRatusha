package com.blackstone.domain.usecases

import com.blackstone.domain.entity.Item
import com.blackstone.domain.repositories.AppRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 09.03.2019
 */
class GetItemCategoryUseCase
@Inject constructor(private val repository: AppRepository) : BaseUseCaseParams<Int, Item>() {

    override suspend fun executeOnBackground(params: Int): Item {
        return repository.getDatabaseService().getItem(params)
    }
}





