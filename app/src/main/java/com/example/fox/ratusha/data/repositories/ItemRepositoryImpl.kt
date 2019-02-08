package com.example.fox.ratusha.data.repositories

import com.example.fox.ratusha.data.db.AppDataBase
import com.example.fox.ratusha.data.db.model.*
import com.example.fox.ratusha.ui.entity.Order
import com.example.fox.ratusha.ui.entity.TownHall
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(val appDataBase: AppDataBase) : ItemRepository {


    override fun setForpostItem(order: List<Order>) {
        Observable.just(appDataBase)
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<AppDataBase> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(appDataBase: AppDataBase) {
                        appDataBase.getForpDao().deleteAll()
                        for (orderList in order[0].itemList) {
                            appDataBase.getForpDao().insert(orderList.transformToItemForpostDao())
                        }

                        appDataBase.getOctDao().deleteAll()
                        for (orderList in order[1].itemList) {
                            appDataBase.getOctDao().insert(orderList.transformToItemOctalDao())
                        }

                        appDataBase.getTownHallDao().deleteAll()
                        for (listOrder in order) {
                            appDataBase.getTownHallDao().insert(listOrder.transformToTownHallDao())
                        }

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }


    override fun getInfoTownHall(): Flowable<List<TownHall>> {
        return appDataBase.getTownHallDao().getAll().map { list -> list.map { it.transformToPresenter() } }
    }
}



