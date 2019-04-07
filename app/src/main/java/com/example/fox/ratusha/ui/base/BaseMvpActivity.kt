package com.example.fox.ratusha.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.example.fox.ratusha.BR

/**
 * @author Evgeny Butov
 * @version 1.0
 * @created 02.02.2019
 */

abstract class BaseMvpActivity<P : BasePresenter<R, *>, R : BaseRouter<*>, B: ViewDataBinding> : BaseActivity() {

    protected lateinit var presenter: P
    protected lateinit var binding: B
    open lateinit var router: R

    abstract fun providePresenter(): P
    abstract fun provideRouter(): R
    abstract fun provideLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        presenter = providePresenter()
        binding = DataBindingUtil.setContentView(this, provideLayoutId())
        binding.setVariable(BR.presenter, presenter)
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