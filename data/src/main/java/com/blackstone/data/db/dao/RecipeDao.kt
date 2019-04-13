package com.blackstone.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.blackstone.domain.entity.ItemRecipe
import com.blackstone.domain.entity.ItemRecipeFull
import io.reactivex.Flowable

/**
 * @author Evgeny Butov
 * @created 08.03.2019
 */

@Dao
interface RecipeDao {

    @Query("SELECT resources.id, image, name, price, number FROM recipe INNER JOIN resources ON recipe.resource = resources.id WHERE recipe = :id")
    fun getRecipe(id: Int): Flowable<List<ItemRecipe>>

    @Query("SELECT resources.id, image, resources.name, price, number, type.name as type FROM recipe " +
            "INNER JOIN resources ON recipe.resource = resources.id " +
            "INNER JOIN item_to_type ON recipe.resource = item_to_type.id " +
            "INNER JOIN type ON item_to_type.type_id = type.id " +
            "WHERE recipe = :id")
    fun getRecipeAlchemy(id: Int): Flowable<List<ItemRecipeFull>>

}