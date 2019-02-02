package com.example.fox.ratusha.ui.base

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {

    fun getIntent(context: Context): Intent {
        return Intent(context, javaClass)
    }

    protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun addToDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
