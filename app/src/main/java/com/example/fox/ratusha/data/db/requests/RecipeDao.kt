package com.example.fox.ratusha.data.db.requests

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.fox.ratusha.ui.entity.ItemRecipe
import io.reactivex.Flowable

/**
 * @author Evgeny Butov
 * @created 08.03.2019
 */

@Dao
interface RecipeDao {

    @Query("SELECT resources.id, image, name, price, number FROM recipe INNER JOIN resources ON recipe.resource = resources.id WHERE recipe = :id")
    fun getRecipe(id: Int): Flowable<List<ItemRecipe>>

}