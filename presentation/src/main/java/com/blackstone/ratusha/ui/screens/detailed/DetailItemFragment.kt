package com.blackstone.ratusha.ui.screens.detailed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackstone.ratusha.R
import com.blackstone.ratusha.databinding.ActivityDetailItemInfoBinding
import com.blackstone.ratusha.ui.base.BaseMvvmFragment
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter

class DetailItemFragment : BaseMvvmFragment<DetailItemViewModel, ControllerRouter, ActivityDetailItemInfoBinding>(){

    companion object {
        private const val ID_ITEM = "ID_ITEM"

        fun getInstance(context: Context, id: Int): Intent {
            return Intent(context, DetailItemFragment::class.java).putExtra(ID_ITEM, id)
        }
    }

    override fun provideViewModel(): DetailItemViewModel {
        return ViewModelProviders.of(this).get(DetailItemViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.activity_detail_item_info

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  viewModel.getItemAndRecipe(intent.getIntExtra(ID_ITEM,0))
        viewModel.getItemAndRecipe(24)

        binding.recyclerInfo.setHasFixedSize(true)
        binding.recyclerInfo.layoutManager = LinearLayoutManager(this.activity)
        binding.recyclerInfo.isNestedScrollingEnabled = false
        binding.recyclerInfo.adapter = viewModel.adapter

    }

    fun hideTextCount() {
        binding.tvCounter.animate().alpha(0.0f).duration = 750
    }

    fun showTextCount() {
        binding.tvCounter.animate().alpha(1.0f).duration = 250
    }

}
