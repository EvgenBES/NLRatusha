package com.example.fox.ratusha.data.repositories

import com.example.fox.ratusha.data.db.AppDataBase
import com.example.fox.ratusha.data.db.model.InfoTownHall
import com.example.fox.ratusha.data.db.model.ItemForpost
import com.example.fox.ratusha.data.db.model.ItemOctal
import com.example.fox.ratusha.data.db.model.transformToPresenter
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
                            appDataBase.getForpDao().insert(ItemForpost(0,
                                    orderList.id,
                                    orderList.itemName,
                                    orderList.urlImage,
                                    orderList.countStart,
                                    orderList.countFinish))
                        }

                        appDataBase.getOctDao().deleteAll()

                        for (orderList in order[1].itemList) {
                            appDataBase.getOctDao().insert(ItemOctal(0,
                                    orderList.id,
                                    orderList.itemName,
                                    orderList.urlImage,
                                    orderList.countStart,
                                    orderList.countFinish))
                        }

                        appDataBase.getTownHallDao().deleteAll()

                        for (listOrder in order) {
                            appDataBase.getTownHallDao().insert(InfoTownHall(
                                    0,
                                    listOrder.townHall.id,
                                    listOrder.townHall.start,
                                    listOrder.townHall.finish,
                                    listOrder.townHall.url
                            ))
                        }

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }


    override fun getInfoTownHall(): Flowable<List<TownHall>> {
        return appDataBase.getTownHallDao().getAll().map { it.map { it.transformToPresenter() } }
    }
}



