package com.blackstone.domain.usecases

import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.domain.repositories.DaoRepository
import com.blackstone.domain.usecases.base.CoroutineUseCase
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 14.03.2019
 */
class GetRecipeItemUseCase
@Inject constructor( private val daoRepository: DaoRepository
) : CoroutineUseCase<List<ItemRecipeFull>>() {

    private var id: Int = 0
    fun setID(id: Int) {
        this.id = id
    }

    override suspend fun executeOnBackground(): List<ItemRecipeFull> {
        return daoRepository.getRecept(id)
    }
}