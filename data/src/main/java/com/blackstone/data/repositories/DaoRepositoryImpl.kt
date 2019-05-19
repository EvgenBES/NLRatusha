package com.blackstone.data.repositories

import com.blackstone.data.db.AppDataBase
import com.blackstone.data.db.entity.*
import com.blackstone.domain.entity.Config
import com.blackstone.domain.entity.TownHall
import com.blackstone.domain.repositories.DaoRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 14.04.2019
 */
class DaoRepositoryImpl @Inject constructor(private val appDataBase: AppDataBase) :
    DaoRepository {

    override fun getTownHall(id: Int): Flowable<TownHall> {
        return appDataBase.getTownHallDao().getTownHall(id).map { it.transformToPresenter() }
    }

    override fun getConfig(): Flowable<Config> {
        return appDataBase.getConfgigDao().getConfig().map { it.transformToConfig() }
    }

    override fun getUpdateConfig(config: Config): Completable {
        return Completable.create {
            appDataBase.getConfgigDao().insert(config.transformToConfigApp())
            it.onComplete()}
    }
}