package com.blackstone.data.repositories

import com.blackstone.data.db.AppDataBase
import com.blackstone.data.db.entity.ItemForpost
import com.blackstone.data.db.entity.transformToItemForpostDao
import com.blackstone.data.db.entity.transformToItemOctalDao
import com.blackstone.data.db.entity.transformToTownHallDao
import com.blackstone.data.extension.mapResponceOrder
import com.blackstone.data.net.RestService
import com.blackstone.domain.entity.Order
import com.blackstone.domain.repositories.ServerRepository
import javax.inject.Inject

class ServerRepositoryImpl @Inject constructor(
    private val appDataBase: AppDataBase, private val apiService: RestService
) : ServerRepository {
    private val emptyOrder = ItemForpost(1, 0, "Пусто", "iw_empty", 0, 0);

    override suspend fun updateDataForpost(): Boolean {
        val result = apiService.getForpost()
        val responseOrder: Order = result.string().mapResponceOrder()
        appDataBase.getForpDao().deleteAll()

        if (responseOrder.itemList.isNotEmpty()) {
            appDataBase.getForpDao().insert(responseOrder.itemList.transformToItemForpostDao())
            appDataBase.getTownHallDao().insert(responseOrder.transformToTownHallDao())
        } else {
            appDataBase.getForpDao().insert(emptyOrder)
        }

        return true
    }

    override suspend fun updateDataOctal(): Boolean {
        val result = apiService.getOctal()

        val responseOrder: Order = result.string().mapResponceOrder()
        appDataBase.getOctDao().deleteAll()

        if (responseOrder.itemList.isNotEmpty()) {
            appDataBase.getOctDao().insert(responseOrder.itemList.transformToItemOctalDao())
            appDataBase.getTownHallDao().insert(responseOrder.transformToTownHallDao())
        } else {
            appDataBase.getForpDao().insert(emptyOrder)
        }

        return true
    }
}