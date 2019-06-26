package com.blackstone.domain.repositories

import com.blackstone.domain.entity.*
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * @author Evgeny Butov
 * @created 14.04.2019
 */
interface DaoRepository {
    fun getConfig(): Flowable<Config>
    fun setConfig(config: Config): Completable

    fun getTownHall(id: Int): Flowable<TownHall>
    fun getInfoTownHall(): Flowable<List<TownHall>>

    fun getItemForpost(): Flowable<List<ItemOrder>>
    fun getItemOctal(): Flowable<List<ItemOrder>>

    suspend fun getCategoryList(): List<ItemCategory>
    suspend fun getItemsCategory(id: Int): List<ItemCategory>

    suspend fun getItem(id: Int): Item
    suspend fun getRecept(id: Int): List<ItemRecipeFull>
    suspend fun getReceptAlchemy(id: Int): List<ItemRecipeFull>
}