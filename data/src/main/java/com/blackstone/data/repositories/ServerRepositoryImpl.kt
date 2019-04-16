package com.blackstone.data.repositories

import com.blackstone.data.db.AppDataBase
import com.blackstone.data.db.entity.*
import com.blackstone.data.extension.mapResponceOrder
import com.blackstone.data.net.RestService
import com.blackstone.domain.entity.*
import com.blackstone.domain.repositories.ServerRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ServerRepositoryImpl @Inject constructor(
    private val appDataBase: AppDataBase, private val apiService: RestService
) : ServerRepository {

    override fun updateDataForpost(): Completable {
        return Completable.fromAction {
            apiService.getForpost().subscribeBy(
                onNext = {
                    val responseOrder: Order = it.string().mapResponceOrder()

                    if (responseOrder.itemList.isNotEmpty()) {
                        appDataBase.getForpDao().deleteAll()

                        for (orderList in responseOrder.itemList) {
                            appDataBase.getForpDao().insert(orderList.transformToItemForpostDao())
                        }
                        appDataBase.getTownHallDao().insert(responseOrder.transformToTownHallDao())
                    }
                },
                onError = {
                    return@subscribeBy
                }
            )
        }
    }

    override fun updateDataOctal(): Completable {
        return Completable.fromAction {
            apiService.getOctal().subscribeBy(
                onNext = {
                    val responseOrder: Order = it.string().mapResponceOrder()

                    if (responseOrder.itemList.isNotEmpty()) {
                        appDataBase.getOctDao().deleteAll()

                        for (orderList in responseOrder.itemList) {
                            appDataBase.getOctDao().insert(orderList.transformToItemOctalDao())
                        }
                        appDataBase.getTownHallDao().insert(responseOrder.transformToTownHallDao())
                    }
                },
                onError = {
                    return@subscribeBy
                }
            )
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
        return appDataBase.getOctDao().getAll().map { list -> list.map { it.transformToPresenter() } }
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



