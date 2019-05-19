package com.blackstone.domain.repositories

import com.blackstone.domain.entity.Config
import com.blackstone.domain.entity.TownHall
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * @author Evgeny Butov
 * @created 14.04.2019
 */
interface DaoRepository {
    fun getTownHall(id: Int): Flowable<TownHall>
    fun getConfig(): Flowable<Config>
    fun getUpdateConfig(config: Config): Completable
}