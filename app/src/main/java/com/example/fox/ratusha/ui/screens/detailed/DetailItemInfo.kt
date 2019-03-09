package com.example.fox.ratusha.ui.screens.detailed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpActivity

class DetailItemInfo : BaseMvpActivity<DetailItemPresenter, DetailItemRouter >(), DetailItemView{

    companion object {
        private const val ID_ITEM = "ID_ITEM"

        fun getInstance(context: Context, id: Int): Intent {
            return Intent(context, DetailItemInfo::class.java).putExtra(DetailItemInfo.ID_ITEM, id)
        }
    }

    override fun providePresenter(): DetailItemPresenter = DetailItemPresenter(this)
    override fun provideRouter(): DetailItemRouter = DetailItemRouter(this)
    override fun provideLayoutId(): Int = R.layout.activity_detail_item_info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getIntExtra(ID_ITEM,0)
    }
}
