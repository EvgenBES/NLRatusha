package com.example.fox.ratusha.data.repositories

import com.example.fox.ratusha.ui.entity.Order
import io.reactivex.Completable

interface ItemRepository : BaseRepository {
    fun setForpostItem(order: List<Order>)
}
