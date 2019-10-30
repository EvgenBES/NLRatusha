package com.blackstone.domain.usecases

import androidx.lifecycle.LiveData
import com.blackstone.domain.entity.TownHall
import com.blackstone.domain.repositories.AppRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 07.02.2019
 */
class GetInfoTownHallUseCase
@Inject constructor(private val repository: AppRepository) {

    fun get(): LiveData<List<TownHall>> {
        return repository.getDatabaseService().getInfoTownHall()
    }

    fun getTownHall(id: Int): LiveData<TownHall> {
        return repository.getDatabaseService().getTownHall(id)
    }
}