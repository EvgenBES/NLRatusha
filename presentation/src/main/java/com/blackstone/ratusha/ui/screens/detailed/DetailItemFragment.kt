package com.blackstone.ratusha.ui.screens.detailed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackstone.ratusha.R
import com.blackstone.ratusha.databinding.FragmentFullInfoBinding
import com.blackstone.ratusha.ui.base.BaseMvvmFragment
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter

class DetailItemFragment : BaseMvvmFragment<DetailItemViewModel, ControllerRouter, FragmentFullInfoBinding>(){

    companion object {
        private const val ID_ITEM = "ID_ITEM"

        fun getInstance(id: Int): Fragment {
            val fragment = DetailItemFragment()
            val bundle = Bundle()
            bundle.putInt(ID_ITEM, id)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun provideViewModel(): DetailItemViewModel {
        return ViewModelProviders.of(this).get(DetailItemViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_full_info

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.arguments?.let {
            viewModel.getItemAndRecipe(it.getInt(ID_ITEM, 0))
        }

        binding.recyclerInfo.setHasFixedSize(true)
        binding.recyclerInfo.layoutManager = LinearLayoutManager(this.activity)
        binding.recyclerInfo.isNestedScrollingEnabled = false
        binding.recyclerInfo.adapter = viewModel.getAdapter()

    }

    fun hideTextCount() {
        binding.tvCounter.animate().alpha(0.0f).duration = 750
    }

    fun showTextCount() {
        binding.tvCounter.animate().alpha(1.0f).duration = 250
    }

}
