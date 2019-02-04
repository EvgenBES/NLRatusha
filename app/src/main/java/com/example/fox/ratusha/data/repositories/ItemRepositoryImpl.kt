package com.example.fox.ratusha.data.repositories

import com.example.fox.ratusha.data.db.AppDataBase
import com.example.fox.ratusha.data.db.model.ItemForpost
import com.example.fox.ratusha.data.db.model.ItemOctal
import com.example.fox.ratusha.ui.entity.Order
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

                        for (orderList in order[0].listItem) {
                            appDataBase.getForpDao().insert(ItemForpost(0,
                                    orderList.id.toInt(),
                                    orderList.itemName,
                                    orderList.urlImage,
                                    orderList.countStart.toInt(),
                                    orderList.countFinish.toInt(),
                                    order[0].startOrder,
                                    order[0].finishOrder))

                        }


                        appDataBase.getOctDao().deleteAll()

                        for (orderList in order[1].listItem) {
                            appDataBase.getOctDao().insert(ItemOctal(0,
                                    orderList.id.toInt(),
                                    orderList.itemName,
                                    orderList.urlImage,
                                    orderList.countStart.toInt(),
                                    orderList.countFinish.toInt(),
                                    order[1].startOrder,
                                    order[1].finishOrder))

                        }
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })


    }

}


