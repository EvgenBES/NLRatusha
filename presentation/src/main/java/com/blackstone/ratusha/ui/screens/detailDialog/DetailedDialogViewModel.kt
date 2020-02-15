package com.blackstone.ratusha.ui.screens.detailDialog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.domain.usecases.recipe.GetRecipeItemUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 18.05.2019
 */
class DetailedDialogViewModel : BaseViewModel<ControllerRouter>() {

    @Inject
    lateinit var getRecipeItemUseCase: GetRecipeItemUseCase

    private val _listAdapter = MutableLiveData<List<ItemRecipeFull>>()
    val listAdapter: LiveData<List<ItemRecipeFull>> = _listAdapter

    init {
        App.appComponent.runInject(this)
    }


    fun getRecipeItem(id: Int) {
        getRecipeItemUseCase.execute(id) {
            onComplete {
                _listAdapter.postValue(it)
            }
            onError { Log.d(TAG, "getRecipeItem message: ${it.message}") }
        }
    }

}
