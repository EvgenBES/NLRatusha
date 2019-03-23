package com.example.fox.ratusha.data.repositories

import com.example.fox.ratusha.ui.entity.*
import io.reactivex.Flowable

interface RatushaRepository {
    fun updateHallTowen(order: List<Order>): Boolean
    fun getInfoTownHall(): Flowable<List<TownHall>>
    fun getTownHall(id: Int): Flowable<TownHall>
    fun getItemForpost(): Flowable<List<ItemOrder>>
    fun getItemOctal(): Flowable<List<ItemOrder>>
    fun getCategoryList(): Flowable<List<ItemCategory>>
    fun getItemsCategory(id: Int): Flowable<List<ItemCategory>>
    fun getRecept(id: Int): Flowable<List<ItemRecipe>>
}
