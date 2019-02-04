package com.example.fox.ratusha.data.repositories

import com.example.fox.ratusha.data.db.AppDataBase
import com.example.fox.ratusha.data.db.model.forpost.ItemForpDao
import com.example.fox.ratusha.data.db.model.forpost.ItemForpResponce
import com.example.fox.ratusha.ui.entity.Order
import io.reactivex.Completable
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
                        for (tempOrder in order) {
                            appDataBase.getForpDao().insert(ItemForpResponce(1, "2","3",4,5,"6","7"))
                        }
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }

}


