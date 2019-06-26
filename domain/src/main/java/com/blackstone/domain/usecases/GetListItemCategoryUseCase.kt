package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.repositories.DaoRepository
import com.blackstone.domain.usecases.base.CoroutineUseCase
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 09.03.2019
 */
class GetListItemCategoryUseCase
@Inject constructor(private val daoRepository: DaoRepository)
    : CoroutineUseCase<List<ItemCategory>>() {

    private var id: Int = 0
    fun setID(id: Int) {
        this.id = id
    }

    override suspend fun executeOnBackground(): List<ItemCategory> {
        return daoRepository.getItemsCategory(id)
    }
}





