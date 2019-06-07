package com.blackstone.data.repositories

import com.blackstone.data.db.AppDataBase
import com.blackstone.data.db.entity.*
import com.blackstone.data.extension.mapResponceOrder
import com.blackstone.data.net.RestService
import com.blackstone.domain.entity.*
import com.blackstone.domain.repositories.ServerRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class ServerRepositoryImpl @Inject constructor(
    private val appDataBase: AppDataBase, private val apiService: RestService
) : ServerRepository {

    override fun updateDataForpost(): Observable<Boolean> {
        return apiService.getForpost().map {
            val responseOrder: Order = it.string().mapResponceOrder()
            appDataBase.getForpDao().deleteAll()

            if (responseOrder.itemList.isNotEmpty()) {
                appDataBase.getForpDao().insert(responseOrder.itemList.transformToItemForpostDao())
                appDataBase.getTownHallDao().insert(responseOrder.transformToTownHallDao())
            } else {
                appDataBase.getForpDao().insert(ItemForpost(1, 0, "Пусто", "iw_empty", 0, 0))
            }
            return@map true
        }
    }

    override fun updateDataOctal(): Observable<Boolean> {
        return apiService.getOctal().map {
            val responseOrder: Order = it.string().mapResponceOrder()
            appDataBase.getOctDao().deleteAll()

            if (responseOrder.itemList.isNotEmpty()) {
                appDataBase.getOctDao().insert(responseOrder.itemList.transformToItemOctalDao())
                appDataBase.getTownHallDao().insert(responseOrder.transformToTownHallDao())
            } else {
                appDataBase.getForpDao().insert(ItemForpost(1, 0, "Пусто", "iw_empty", 0, 0))
            }
            return@map true
        }
    }

    override fun getInfoTownHall(): Flowable<List<TownHall>> {
        return appDataBase.getTownHallDao().getAll().map { list -> list.map { it.transformToPresenter() } }
    }

    override fun getTownHall(id: Int): Flowable<TownHall> {
        return appDataBase.getTownHallDao().getTownHall(id).map { it.transformToPresenter() }
    }

    override fun getItemForpost(): Flowable<List<ItemOrder>> {
        return appDataBase.getForpDao().getAll()
    }

    override fun getItemOctal(): Flowable<List<ItemOrder>> {
        return appDataBase.getOctDao().getAll()
    }

    override fun getCategoryList(): Flowable<List<ItemCategory>> {
        return appDataBase.getCategoryDao().getAll().map { list -> list.map { it.transformToPresenter() } }
    }

    override fun getItemsCategory(id: Int): Flowable<List<ItemCategory>> {
        return appDataBase.getItemsDao().getItemsCategory(id).map { list -> list.map { it.transformToItemCategory() } }
    }

    override fun getItem(id: Int): Flowable<Item> {
        return appDataBase.getItemsDao().getItem(id).map { it.transformToPresenter() }
    }

    override fun getRecept(id: Int): Flowable<List<ItemRecipeFull>> {
        return appDataBase.getRecipeDao().getRecipe(id).map { list -> list.map { it.transformToFull() } }
    }

    override fun getReceptAlchemy(id: Int): Flowable<List<ItemRecipeFull>> {
        return appDataBase.getRecipeDao().getRecipeAlchemy(id)
    }
}



