package com.blackstone.ratusha.ui.screens.detailDialog

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackstone.ratusha.R
import com.blackstone.ratusha.databinding.DialogDetailedBinding
import com.blackstone.ratusha.databinding.DialogSettingsBinding
import com.blackstone.ratusha.ui.adapter.recipe.RecipeAdapter
import com.blackstone.ratusha.ui.base.BaseMvvmDialog
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter

/**
 * @author Evgeny Butov
 * @created 18.05.2019
 */
class DetailedDialog: BaseMvvmDialog<DetailedDialogViewModel, ControllerRouter, DialogDetailedBinding>() {

    private val adapter = RecipeAdapter()

    override fun provideLayoutId(): Int = R.layout.dialog_detailed
    override fun provideViewModel(): DetailedDialogViewModel {
        return ViewModelProviders.of(this).get(DetailedDialogViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.SettingDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()

        setUpObserver()

        viewModel.getRecipeItem(1803)
    }


    private fun setUpRecycler() {
        binding.recyclerInfo.setHasFixedSize(true)
        binding.recyclerInfo.layoutManager = LinearLayoutManager(this.activity)
        binding.recyclerInfo.isNestedScrollingEnabled = false
        binding.recyclerInfo.adapter = adapter
    }

    private fun setUpObserver() {
        viewModel.apply {
            listAdapter.observe(viewLifecycleOwner, Observer { list ->
                adapter.setItems(list)
            })
        }
    }



    companion object{
        const val DETAILED_DIALOG = "DETAILED_DIALOG"
    }
}