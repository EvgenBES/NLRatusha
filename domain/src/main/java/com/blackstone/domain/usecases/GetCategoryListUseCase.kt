package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.repositories.DaoRepository
import com.blackstone.domain.usecases.base.CoroutineUseCase
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 08.03.2019
 */
class GetCategoryListUseCase
@Inject constructor( private val daoRepository: DaoRepository)
    : CoroutineUseCase<List<ItemCategory>>() {

    override suspend fun executeOnBackground(): List<ItemCategory> {
        return daoRepository.getCategoryList()
    }
}