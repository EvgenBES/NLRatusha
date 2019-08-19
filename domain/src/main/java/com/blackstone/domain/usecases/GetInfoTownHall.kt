package com.blackstone.domain.usecases

import androidx.lifecycle.LiveData
import com.blackstone.domain.entity.TownHall
import com.blackstone.domain.repositories.DaoRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 07.02.2019
 */
class GetInfoTownHall
    @Inject constructor(private val daoRepository: DaoRepository) {

    fun get(): LiveData<List<TownHall>> {
        return daoRepository.getInfoTownHall()
    }

    fun getTownHall(id: Int): LiveData<TownHall> {
        return daoRepository.getTownHall(id)
    }
}