package com.blackstone.ratusha.ui.screens.settings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.blackstone.ratusha.R
import com.blackstone.ratusha.databinding.DialogSettingsBinding
import com.blackstone.ratusha.ui.base.mvvm.BaseMvvmDialog
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter

/**
 * @author Evgeny Butov
 * @created 18.05.2019
 */
class Settings: BaseMvvmDialog<SettingsModel, ControllerRouter, DialogSettingsBinding>() {

    override fun provideLayoutId(): Int = R.layout.dialog_settings
    override fun provideViewModel(): SettingsModel {
        return ViewModelProviders.of(this).get(SettingsModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.closeDialog.observe(this, Observer {
            it?.let { result ->
                if (result) dialog.dismiss()
            }
        })
    }

}