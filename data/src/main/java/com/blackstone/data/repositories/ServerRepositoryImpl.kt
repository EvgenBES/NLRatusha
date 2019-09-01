package com.blackstone.data.repositories

import com.blackstone.data.db.AppDataBase
import com.blackstone.data.db.entity.Meta
import com.blackstone.data.db.entity.transformToItemForpostDao
import com.blackstone.data.db.entity.transformToItemOctalDao
import com.blackstone.data.db.entity.transformToTownHallDao
import com.blackstone.data.extension.mapResponceOrder
import com.blackstone.data.net.RestService
import com.blackstone.domain.entity.Order
import com.blackstone.domain.repositories.ServerRepository
import java.util.*
import javax.inject.Inject

class ServerRepositoryImpl @Inject constructor(
    private val appDataBase: AppDataBase, private val apiService: RestService
) : ServerRepository {

    override suspend fun updateDataForpost(): Boolean {
        val result = apiService.getForpost()
        val responseOrder: Order = result.string().mapResponceOrder()
        appDataBase.getForpDao().deleteAll()

        if (responseOrder.itemList.isNotEmpty()) {
            appDataBase.getForpDao().insert(responseOrder.itemList.transformToItemForpostDao())
            appDataBase.getTownHallDao().insert(responseOrder.transformToTownHallDao())
        }

        appDataBase.getMetaDao().insert(Meta())
        return true
    }

    override suspend fun updateDataOctal(): Boolean {
        val result = apiService.getOctal()

        val responseOrder: Order = result.string().mapResponceOrder()
        appDataBase.getOctDao().deleteAll()

        if (responseOrder.itemList.isNotEmpty()) {
            appDataBase.getOctDao().insert(responseOrder.itemList.transformToItemOctalDao())
            appDataBase.getTownHallDao().insert(responseOrder.transformToTownHallDao())
        }

        appDataBase.getMetaDao().insert(Meta())
        return true
    }
}