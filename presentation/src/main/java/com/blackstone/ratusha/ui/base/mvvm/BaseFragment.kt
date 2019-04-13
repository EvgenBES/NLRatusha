package com.blackstone.ratusha.ui.base.mvvm

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
abstract class BaseFragment : Fragment() {
    protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun addToDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}