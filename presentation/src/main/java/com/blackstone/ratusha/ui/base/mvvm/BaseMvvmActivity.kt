package com.blackstone.ratusha.ui.base.mvvm

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.blackstone.ratusha.BR


/**
 * @author Evgeny Butov
 * @created 13.04.2019
 */
abstract class BaseMvvmActivity<VM : BaseViewModel<R>, R : BaseRouter<*>, B : ViewDataBinding> : BaseActivity() {

    protected open lateinit var viewModel: VM
    protected lateinit var binding: B
    open lateinit var router: R

    abstract fun prodiveViewModel(): VM
    abstract fun provideLayoutId(): Int
    abstract fun provideRouter(): R

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = prodiveViewModel()
        binding = DataBindingUtil.setContentView(this, provideLayoutId())
        binding.setVariable(BR.viewModel, viewModel)
        router = provideRouter()
    }

    override fun onResume() {
        super.onResume()
        viewModel.addRouter(router)
    }

    override fun onPause() {
        super.onPause()
    }
}