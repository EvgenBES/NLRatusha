package com.blackstone.domain.repositories

import androidx.lifecycle.LiveData
import com.blackstone.domain.entity.*

/**
 * @author Evgeny Butov
 * @created 14.04.2019
 */
interface DaoRepository {
    fun getMeta(): LiveData<MetaInfo>

    fun getTownHall(id: Int): LiveData<TownHall>
    fun getInfoTownHall(): LiveData<List<TownHall>>

    fun getItemForpost(): LiveData<List<ItemOrder>>
    fun getItemOctal(): LiveData<List<ItemOrder>>

    suspend fun getCategoryList(): List<ItemCategory>
    suspend fun getItemsCategory(id: Int): List<ItemCategory>

    suspend fun getItem(id: Int): Item
    suspend fun getRecept(id: Int): List<ItemRecipeFull>
    suspend fun getReceptAlchemy(id: Int): List<ItemRecipeFull>
}