package com.blackstone.ratusha.ui.base.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        //AndroidInjection.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
