package com.blackstone.ratusha.ui.screens.detailed

import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackstone.ratusha.R
import com.blackstone.ratusha.ui.base.mvvm.BaseMvvmActivity
import com.blackstone.ratusha.databinding.ActivityDetailItemInfoBinding

class DetailItemInfo : BaseMvvmActivity<DetailItemModel, DetailItemRouter, ActivityDetailItemInfoBinding>(){

    companion object {
        private const val ID_ITEM = "ID_ITEM"

        fun getInstance(context: Context, id: Int): Intent {
            return Intent(context, DetailItemInfo::class.java).putExtra(ID_ITEM, id)
        }
    }

    override fun provideViewModel(): DetailItemModel {
        return ViewModelProviders.of(this).get(DetailItemModel::class.java)
    }
    override fun provideRouter(): DetailItemRouter = DetailItemRouter(this)
    override fun provideLayoutId(): Int = R.layout.activity_detail_item_info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getItemAndRecipe(intent.getIntExtra(ID_ITEM,0))

        binding.recyclerInfo.setHasFixedSize(true)
        binding.recyclerInfo.layoutManager = LinearLayoutManager(this)
        binding.recyclerInfo.itemAnimator = DefaultItemAnimator()
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
