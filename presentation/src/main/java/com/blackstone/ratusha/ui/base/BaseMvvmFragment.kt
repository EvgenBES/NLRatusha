package com.blackstone.ratusha.ui.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blackstone.ratusha.BR

/**
 * @author Evgeny Butov
 * @created 13.04.2019
 */
abstract class BaseMvvmFragment<VM : BaseViewModel<R>, R : BaseRouter<*>, B : ViewDataBinding> : Fragment() {

    protected var TAG: String = "RATUSHA ${this::class.java.simpleName}"

    protected lateinit var viewModel: VM
    protected lateinit var binding: B
    protected var router: R? = null

    abstract fun provideViewModel(): VM
    abstract fun provideLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, provideLayoutId(), container, false)
        viewModel = provideViewModel()
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
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.removeRouter()
    }
}