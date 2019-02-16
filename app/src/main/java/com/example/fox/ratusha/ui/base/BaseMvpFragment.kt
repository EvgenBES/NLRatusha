package com.example.fox.ratusha.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author Evgeny Butov
 * @since 16.02.2019
 */
abstract class BaseMvpFragment<P : BasePresenter<R, *>, R : BaseRouter<*>> : BaseFragment() {

    protected lateinit var presenter: P
    protected var router: R? = null

    abstract fun providePresenter(): P
    abstract fun provideLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = providePresenter()
        return inflater.inflate(provideLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is BaseMvpActivity<*, *>) {
            router = (activity as BaseMvpActivity<*, *>).router as R
        }
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