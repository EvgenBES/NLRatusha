package com.example.fox.ratusha.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.fox.ratusha.data.db.entity.InfoTownHall
import io.reactivex.Flowable

/**
 * @author Evgeny Butov
 * @created 07.02.2019
 */

@Dao
interface TownHallDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(infoTownHall: InfoTownHall)

    @Query("DELETE FROM townHall")
    fun deleteAll()

    @Query("SELECT * FROM townHall")
    fun getAll(): Flowable<List<InfoTownHall>>
}