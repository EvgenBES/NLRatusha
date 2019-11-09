package com.blackstone.data.repositories

import android.content.Context
import com.blackstone.data.shared.SharedPreferenceProvider
import com.blackstone.domain.repositories.SharedProvider
import com.blackstone.domain.entity.Config
import javax.inject.Inject

class SharedProviderImpl @Inject constructor(context: Context):
    SharedPreferenceProvider(context), SharedProvider {

    companion object{
        const val CONFIG_TP_FORPOST = "CONFIG_TP_FORPOST"
        const val CONFIG_TP_OCTAL = "CONFIG_TP_OCTAL"
        const val CONFIG_CLOSE_FORPOST = "CONFIG_CLOSE_FORPOST"
        const val CONFIG_CLOSE_OCTAL = "CONFIG_CLOSE_OCTAL"
    }


    override fun updateNotificationSettings(config: Config) {
        sharedPreferences.edit().putBoolean(CONFIG_TP_FORPOST, config.tpForpost).apply()
        sharedPreferences.edit().putBoolean(CONFIG_TP_OCTAL, config.tpOctal).apply()
        sharedPreferences.edit().putBoolean(CONFIG_CLOSE_FORPOST, config.closeForpost).apply()
        sharedPreferences.edit().putBoolean(CONFIG_CLOSE_OCTAL, config.closeOctal).apply()
    }

    override fun getNotificationSettings(): Config {
       return Config(
           tpForpost = sharedPreferences.getBoolean(CONFIG_TP_FORPOST, false),
           tpOctal = sharedPreferences.getBoolean(CONFIG_TP_OCTAL, false),
           closeForpost = sharedPreferences.getBoolean(CONFIG_CLOSE_FORPOST, false),
           closeOctal = sharedPreferences.getBoolean(CONFIG_CLOSE_OCTAL, false)
       )
    }
}