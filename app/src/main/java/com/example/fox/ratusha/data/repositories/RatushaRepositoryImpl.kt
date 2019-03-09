package com.example.fox.ratusha.data.repositories

import android.util.Log
import com.example.fox.ratusha.data.db.AppDataBase
import com.example.fox.ratusha.data.db.entity.*
import com.example.fox.ratusha.ui.entity.ItemCategory
import com.example.fox.ratusha.ui.entity.ItemOrder
import com.example.fox.ratusha.ui.entity.Order
import com.example.fox.ratusha.ui.entity.TownHall
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RatushaRepositoryImpl @Inject constructor(val appDataBase: AppDataBase) : RatushaRepository {
    override fun setForpostItem(order: List<Order>) {
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
                        },
                        onError = { Log.e("ItemRepositoryImpl", "setForpostItem error") }
                )
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
}



