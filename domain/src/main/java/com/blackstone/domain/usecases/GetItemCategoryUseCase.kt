package com.blackstone.domain.usecases

import com.blackstone.domain.entity.Item
import com.blackstone.domain.repositories.DaoRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 09.03.2019
 */
class GetItemCategoryUseCase
    @Inject constructor(private val daoRepository: DaoRepository)
    : BaseUseCaseParams<Int, Item>() {

    override suspend fun executeOnBackground(params: Int): Item {
        return daoRepository.getItem(params)
    }
}





