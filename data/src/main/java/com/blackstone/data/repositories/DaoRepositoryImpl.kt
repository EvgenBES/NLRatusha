package com.blackstone.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.blackstone.data.db.AppDataBase
import com.blackstone.data.db.entity.*
import com.blackstone.domain.entity.*
import com.blackstone.domain.repositories.DaoRepository
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 14.04.2019
 */
class DaoRepositoryImpl @Inject constructor(private val appDataBase: AppDataBase) : DaoRepository {

    override fun getConfig(): LiveData<Config> {
        return Transformations.map(appDataBase.getConfigDao().getConfig()) { config -> config?.transformToConfig() }
    }

    override fun setConfig(config: Config) {
        return appDataBase.getConfigDao().insert(config.transformToConfigApp())
    }

    override fun getMeta(): LiveData<MetaInfo> {
        return appDataBase.getMetaDao().getMeta()
    }

    override fun getTownHall(id: Int): LiveData<TownHall> {
        return appDataBase.getTownHallDao().getTownHall(id)
    }

    override fun getInfoTownHall(): LiveData<List<TownHall>> {
        return Transformations.map(appDataBase.getTownHallDao().getAll()) { list -> list.map { it.transformToPresenter() } }
    }

    override fun getItemForpost(): LiveData<List<ItemOrder>> {
        return Transformations.map(appDataBase.getForpDao().getAll()) { list -> list.map { it.transformToPresenter() } }
    }

    override fun getItemOctal(): LiveData<List<ItemOrder>> {
        return  Transformations.map(appDataBase.getOctDao().getAll()) { list -> list.map { it.transformToPresenter() } }
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