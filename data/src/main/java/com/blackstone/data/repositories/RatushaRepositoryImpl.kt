package com.blackstone.data.repositories

import android.util.Log
import com.blackstone.data.db.AppDataBase
import com.blackstone.data.db.entity.*
import com.blackstone.data.net.RestService
import com.blackstone.domain.entity.*
import com.blackstone.domain.repositories.RatushaRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RatushaRepositoryImpl @Inject constructor(private val appDataBase: AppDataBase, private val apiService: RestService) :
    RatushaRepository {

    override fun updateHallTowen(order: List<Order>): Boolean {
      var result = false
        Observable.just(appDataBase)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            appDataBase.getForpDao().deleteAll()
                            for (orderList in order[0].itemList) {
                                appDataBase.getForpDao().insert(orderList.transformToItemForpostDao())
                            }

                            appDataBase.getOctDao().deleteAll()
                            for (orderList in order[1].itemList) {
                                appDataBase.getOctDao().insert(orderList.transformToItemOctalDao())
                            }

                            for (listOrder in order) {
                                appDataBase.getTownHallDao().insert(listOrder.transformToTownHallDao())
                            }

                            result = true
                        },
                        onError = {
                            Log.e("ItemRepositoryImpl", "updateHallTowen error")
                            result = false
                        }
                )
        return result
    }

    override fun getInfoTownHall(): Flowable<List<TownHall>> {
        return appDataBase.getTownHallDao().getAll().map { list -> list.map { it.transformToPresenter() } }
    }

    override fun getTownHall(id: Int): Flowable<TownHall> {
        return appDataBase.getTownHallDao().getTownHall(id).map { it.transformToPresenter()  }
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


