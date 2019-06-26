package com.blackstone.data.repositories

import com.blackstone.data.db.AppDataBase
import com.blackstone.data.db.entity.*
import com.blackstone.domain.entity.*
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

    override fun getConfig(): Flowable<Config> {
        return appDataBase.getConfgigDao().getConfig().map { it.transformToConfig() }
    }

    override fun setConfig(config: Config): Completable {
        return Completable.create {
            appDataBase.getConfgigDao().insert(config.transformToConfigApp())
            it.onComplete()}
    }

    override fun getTownHall(id: Int): Flowable<TownHall> {
        return appDataBase.getTownHallDao().getTownHall(id).map { it.transformToPresenter() }
    }

    override fun getInfoTownHall(): Flowable<List<TownHall>> {
        return appDataBase.getTownHallDao().getAll().map { list -> list.map { it.transformToPresenter() } }
    }

    override fun getItemForpost(): Flowable<List<ItemOrder>> {
        return appDataBase.getForpDao().getAll()
    }

    override fun getItemOctal(): Flowable<List<ItemOrder>> {
        return appDataBase.getOctDao().getAll()
    }

    override suspend fun getCategoryList(): List<ItemCategory> {
        return appDataBase.getCategoryDao().getAll().map { it.transformToPresenter() }
    }

    override suspend fun getItemsCategory(id: Int): List<ItemCategory> {
        return appDataBase.getItemsDao().getItemsCategory(id).map { it.transformToItemCategory() }
    }

    override suspend fun getItem(id: Int): Item {
        return appDataBase.getItemsDao().getItem(id).transformToPresenter()
    }

    override suspend fun getRecept(id: Int): List<ItemRecipeFull> {
        return appDataBase.getRecipeDao().getRecipe(id).map { it.transformToFull()  }
    }

    override suspend fun getReceptAlchemy(id: Int): List<ItemRecipeFull> {
        return appDataBase.getRecipeDao().getRecipeAlchemy(id)
    }
}