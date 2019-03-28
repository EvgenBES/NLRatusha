package com.example.fox.ratusha.data.repositories

import android.util.Log
import com.example.fox.ratusha.data.db.AppDataBase
import com.example.fox.ratusha.data.db.entity.*
import com.example.fox.ratusha.data.network.RestService
import com.example.fox.ratusha.ui.entity.*
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RatushaRepositoryImpl @Inject constructor(private val appDataBase: AppDataBase, private val apiService: RestService) : RatushaRepository {

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
        return appDataBase.getForpDao().getAll().map { list -> list.map { it.transformToPresenter() } }
    }

    override fun getItemOctal(): Flowable<List<ItemOrder>> {
        return appDataBase.getOctDao().getAll().map { list -> list.map { it.transformToPresenter() } }
    }

    override fun getCategoryList(): Flowable<List<ItemCategory>> {
        return appDataBase.getCategoryDao().getAll().map { list -> list.map { it.transformToPresenter() } }
    }

    override fun getItemsCategory(id: Int): Flowable<List<ItemCategory>> {
        return appDataBase.getItemsDao().getItemsCategory(id).map { list -> list.map { it.transformToPresenter() } }
    }

    override fun getItem(id: Int): Flowable<Items> {
        return appDataBase.getItemsDao().getItem(id)
    }

    override fun getRecept(id: Int): Flowable<List<ItemRecipe>> {
       return appDataBase.getRecipeDao().getRecipe(id)
    }
}



