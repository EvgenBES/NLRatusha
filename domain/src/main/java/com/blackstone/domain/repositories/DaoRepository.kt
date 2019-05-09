package com.blackstone.domain.repositories

import com.blackstone.domain.entity.TownHall
import io.reactivex.Flowable

/**
 * @author Evgeny Butov
 * @created 14.04.2019
 */
interface DaoRepository {
    fun getTownHall(id: Int): Flowable<TownHall>
}