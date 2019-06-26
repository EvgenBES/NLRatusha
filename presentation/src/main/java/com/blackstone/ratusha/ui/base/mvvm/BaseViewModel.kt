package com.blackstone.ratusha.ui.base.mvvm

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Evgeny Butov
 * @created 13.04.2019
 */
abstract class BaseViewModel<R : BaseRouter<*>> : ViewModel() {

    protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    protected var router: R? = null
    public  fun addRouter(router: R?){
        this.router = router
    }

    public fun removeRouter() {
        this.router = null
    }

    protected fun addToDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    open fun onResume() {
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}