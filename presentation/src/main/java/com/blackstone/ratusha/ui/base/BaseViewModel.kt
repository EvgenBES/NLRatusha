package com.blackstone.ratusha.ui.base

import androidx.lifecycle.ViewModel

/**
 * @author Evgeny Butov
 * @created 13.04.2019
 */
abstract class BaseViewModel<R : BaseRouter<*>> : ViewModel() {

    protected var TAG: String = "RATUSHA ${this::class.java.simpleName}"

    protected var router: R? = null
    public  fun addRouter(router: R?){
        this.router = router
    }

    public fun removeRouter() {
        this.router = null
    }


    open fun onResume() {
    }

    override fun onCleared() {
        super.onCleared()
    }
}