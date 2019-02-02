package com.example.fox.ratusha.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<R : BaseRouter<*>, V : BaseView> {
    protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    protected var router: R? = null
    protected var view: V? = null

    constructor(view: V) {
        this.view = view
    }

    constructor(router: R, view: V) {
        this.router = router
        this.view = view
    }

    public fun addView(view: V) {
        this.view = view
    }

    public fun removeView() {
        this.view = null
    }

    public fun addRouter(router: R?) {
        this.router = router
    }

    public fun removeRouter() {
        this.router = null
    }

    protected fun addToDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    public open fun onStart() {}
    public open fun onResume() {}
    public open fun onPause() {}
    public open fun onStop() {}

    public open fun onDestroy() {
        compositeDisposable.clear()
    }

}