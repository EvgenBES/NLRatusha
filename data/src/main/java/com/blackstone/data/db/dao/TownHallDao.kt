package com.blackstone.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blackstone.data.db.entity.InfoTownHall
import com.blackstone.domain.entity.TownHall

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
    fun getAll(): LiveData<List<InfoTownHall>>

    @Query("SELECT idTown as id, *  FROM $TABLE_NAME WHERE idTown = :id LIMIT 1")
    fun getTownHall(id: Int): LiveData<TownHall>
}