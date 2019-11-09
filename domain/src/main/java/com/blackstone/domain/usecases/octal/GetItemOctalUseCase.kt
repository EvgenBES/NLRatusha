package com.blackstone.domain.usecases.octal

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.repositories.AppRepository
import com.blackstone.domain.utils.DomainUtils.sortItemOrder
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 08.02.2019
 */
class GetItemOctalUseCase
@Inject constructor(private val repository: AppRepository) {

    fun getAllItemOrder(): LiveData<List<ItemOrder>> {
        return Transformations.map(repository.getDatabaseService().getItemOctal()) { sortItemOrder(it) }
    }
}