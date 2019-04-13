package com.blackstone.ratusha.ui.base.mvp

import android.os.Bundle
import com.blackstone.ratusha.ui.base.mvvm.BaseActivity
import com.blackstone.ratusha.ui.base.mvvm.BaseRouter

/**
 * @author Evgeny Butov
 * @version 1.0
 * @created 02.02.2019
 */

abstract class BaseMvpActivity<P : BasePresenter<R, *>, R : BaseRouter<*>> : BaseActivity() {

    protected lateinit var presenter: P
    open lateinit var router: R

    abstract fun providePresenter(): P
    abstract fun provideRouter(): R
    abstract fun provideLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        presenter = providePresenter()
        router = provideRouter()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.addRouter(router)
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.removeRouter()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

}