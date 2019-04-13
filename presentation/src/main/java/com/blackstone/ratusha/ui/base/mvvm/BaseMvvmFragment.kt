package com.blackstone.ratusha.ui.base.mvvm

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blackstone.ratusha.BR

/**
 * @author Evgeny Butov
 * @created 13.04.2019
 */
abstract class BaseMvvmFragment<VM : BaseViewModel<R>, R : BaseRouter<*>, B : ViewDataBinding> : BaseFragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: B
    protected var router: R? = null

    abstract fun prodiveViewModel(): VM
    abstract fun provideLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, provideLayoutId(), container, false)
        viewModel = prodiveViewModel()
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is BaseMvvmActivity<*, *, *>) {
            router = (activity as BaseMvvmActivity<*, *, *>).router as R
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.addRouter(router)
    }

    override fun onPause() {
        super.onPause()
        viewModel.removeRouter()
    }
}