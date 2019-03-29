package com.example.fox.ratusha.ui.screens.detailed

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_detail_item_info.*

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

        presenter.getItemAndRecipe(intent.getIntExtra(ID_ITEM,0))

        recyclerInfo.setHasFixedSize(true)
        recyclerInfo.layoutManager = LinearLayoutManager(this)
        recyclerInfo.itemAnimator = DefaultItemAnimator()
        recyclerInfo.isNestedScrollingEnabled = false
        recyclerInfo.adapter = presenter.adapter
    }

    override fun setTotal(totalCost: String) {
        tv_total.text = totalCost
    }

    @SuppressLint("SetTextI18n")
    override fun setItem(itemName: String, itemPrice: Int, itemImage: String, itemReputation: Double, itemCountRep: Int) {
        val resourceId = applicationContext.resources.getIdentifier("ic_$itemImage", "drawable", applicationContext.packageName)
        iv_item_image.setImageResource(resourceId)
        tv_item_name.text = itemName
        tv_item_price.text = "Цена: $itemPrice / "
        tv_item_rep.text = "x$itemReputation ($itemCountRep)"
    }
}
