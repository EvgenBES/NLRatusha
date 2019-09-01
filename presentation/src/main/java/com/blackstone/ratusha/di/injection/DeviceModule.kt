package com.blackstone.ratusha.di.injection

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.blackstone.device.notification.NotificationBuilder
import com.blackstone.device.notification.Notifications
import com.blackstone.device.notification.NotificationsImpl
import com.blackstone.domain.repositories.DaoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Evgeny Butov
 * @created 01.09.2019
 */
@Module
class DeviceModule {

    @Singleton
    @Provides
    fun provideNotifications(notificationManagerCompat: NotificationManagerCompat,
                             notificationBuilder: NotificationBuilder,
                             daoRepository: DaoRepository): Notifications {
        return NotificationsImpl(notificationManagerCompat, notificationBuilder, daoRepository)
    }

    @Singleton
    @Provides
    fun provideNotificationManagerCompat(context: Context): NotificationManagerCompat {
        return NotificationManagerCompat.from(context)
    }

    @Singleton
    @Provides
    fun provideNotificationBuilder(context: Context): NotificationBuilder {
        return NotificationBuilder(context)
    }
}