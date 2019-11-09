package com.blackstone.domain.repositories

import com.blackstone.domain.entity.Config


/**
 * @author Evgeny Butov
 * @created 09.11.2019
 */
interface SharedProvider {
    fun updateNotificationSettings(config: Config)
    fun getNotificationSettings():Config
}