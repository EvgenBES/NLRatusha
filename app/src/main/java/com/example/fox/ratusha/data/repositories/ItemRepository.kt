package com.example.fox.ratusha.data.repositories

import com.example.fox.ratusha.ui.entity.ItemOrder
import com.example.fox.ratusha.ui.entity.Order
import com.example.fox.ratusha.ui.entity.TownHall
import io.reactivex.Flowable

interface ItemRepository {
    fun setForpostItem(order: List<Order>)
    fun getInfoTownHall(): Flowable<List<TownHall>>
    fun getItemForpost(): Flowable<List<ItemOrder>>
    fun getItemOctal(): Flowable<List<ItemOrder>>
}
