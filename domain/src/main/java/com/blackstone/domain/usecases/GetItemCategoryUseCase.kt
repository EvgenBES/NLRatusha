package com.blackstone.domain.usecases

import com.blackstone.domain.entity.Item
import com.blackstone.domain.repositories.DaoRepository
import com.blackstone.domain.usecases.base.CoroutineUseCase
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 09.03.2019
 */
class GetItemCategoryUseCase
@Inject constructor(private val daoRepository: DaoRepository)
    : CoroutineUseCase<Item>() {

    private var id: Int = 0
    fun setID(id: Int) {
        this.id = id
    }

    override suspend fun executeOnBackground(): Item {
        return daoRepository.getItem(id)
    }
}





