package com.example.fox.ratusha.ui.base

import android.os.Bundle

/**
 * @author Evgeny Butov
 * @version 1.0
 * @since 02.02.2019
 */

abstract class BaseMvpActivity<P : BasePresenter<R, *>, R : BaseRouter<*>> : BaseActivity() {

    protected lateinit var presenter: P
    lateinit var router: R

    abstract fun providePresenter(): P
    abstract fun provideRouter(): R
    abstract fun provideLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        router = provideRouter()
        presenter = providePresenter()
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