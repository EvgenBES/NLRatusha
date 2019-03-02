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

    companion object {
        const val TABLE_NAME = "townHall"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(infoTownHall: InfoTownHall)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Flowable<List<InfoTownHall>>

    @Query("SELECT * FROM $TABLE_NAME WHERE idTown = :id LIMIT 1")
    fun getTownHall(id: Int): Flowable<InfoTownHall>
}