package com.blackstone.ratusha.ui.screens.settings

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.blackstone.ratusha.R
import com.blackstone.ratusha.databinding.DialogSettingsBinding
import com.blackstone.ratusha.ui.base.BaseMvvmDialog
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter

/**
 * @author Evgeny Butov
 * @created 18.05.2019
 */
class Settings: BaseMvvmDialog<SettingsModel, ControllerRouter, DialogSettingsBinding>() {

    companion object{
        const val SETTING = "Settings"
    }

    override fun provideLayoutId(): Int = R.layout.dialog_settings
    override fun provideViewModel(): SettingsModel {
        return ViewModelProviders.of(this).get(SettingsModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialog)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getCloseDialog().observe(this, Observer {
            it?.let { result ->
                if (result) dialog?.dismiss()
            }
        })
    }

}