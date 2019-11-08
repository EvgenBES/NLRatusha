package com.blackstone.ratusha.ui.adapter.recipe

import android.view.ViewGroup
import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.domain.entity.TypeRecipe
import com.blackstone.ratusha.ui.base.recycler.BaseRecyclerAdapter

/**
 * @author Evgeny Butov
 * @created 21.08.2019
 */
class RecipeAdapter: BaseRecyclerAdapter<ItemRecipeFull, RecipeViewModel>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecipeViewHolder {
        return RecipeViewHolder.create(viewGroup, RecipeViewModel())
    }
}