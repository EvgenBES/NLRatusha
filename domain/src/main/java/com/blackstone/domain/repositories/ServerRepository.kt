package com.blackstone.domain.repositories

import com.blackstone.domain.entity.*
import io.reactivex.Completable
import io.reactivex.Flowable

interface ServerRepository {
    fun updateDataForpost(): Completable
    fun updateDataOctal(): Completable

    fun getInfoTownHall(): Flowable<List<TownHall>>
    fun getTownHall(id: Int): Flowable<TownHall>
    fun getItemForpost(): Flowable<List<ItemOrder>>
    fun getItemOctal(): Flowable<List<ItemOrder>>
    fun getCategoryList(): Flowable<List<ItemCategory>>
    fun getItemsCategory(id: Int): Flowable<List<ItemCategory>>
    fun getItem(id: Int): Flowable<Item>
    fun getRecept(id: Int): Flowable<List<ItemRecipeFull>>
    fun getReceptAlchemy(id: Int): Flowable<List<ItemRecipeFull>>
}
