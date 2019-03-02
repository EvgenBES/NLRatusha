package com.example.fox.ratusha.ui.screens.detailItemInfo

import android.os.Bundle
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpActivity

class DetailItemInfo : BaseMvpActivity<DetailItemPresenter, DetailItemRouter >(), DetailItemView{

    override fun providePresenter(): DetailItemPresenter = DetailItemPresenter(this)
    override fun provideRouter(): DetailItemRouter = DetailItemRouter(this)
    override fun provideLayoutId(): Int = R.layout.activity_detail_item_info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getIntExtra("ID",0)
    }
}
