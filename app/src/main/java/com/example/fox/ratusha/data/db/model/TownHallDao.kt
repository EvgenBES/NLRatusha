package com.example.fox.ratusha.data.db.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * @author Evgeny Butov
 * @since 07.02.2019
 */

@Dao
interface TownHallDao {
    @Insert
    fun insert(infoTownHall: InfoTownHall)

    @Query("DELETE FROM townHall")
    abstract fun deleteAll()

    @Query ("SELECT * FROM townHall")
    fun getAll(): Flowable<List<InfoTownHall>>
}