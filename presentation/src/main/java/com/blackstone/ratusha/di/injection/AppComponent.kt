package com.blackstone.ratusha.di.injection


import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.services.NotificationService
import com.blackstone.ratusha.ui.screens.controller.ControllerModel
import com.blackstone.ratusha.ui.screens.detailDialog.DetailedDialogViewModel
import com.blackstone.ratusha.ui.screens.detailed.DetailItemViewModel
import com.blackstone.ratusha.ui.screens.forpost.FForpostModel
import com.blackstone.ratusha.ui.screens.information.FInformationModel
import com.blackstone.ratusha.ui.screens.main.FMainModel
import com.blackstone.ratusha.ui.screens.octal.FOctalModel
import com.blackstone.ratusha.ui.screens.settings.SettingsModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, DataModule::class, DeviceModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
    fun runInject(notificationService: NotificationService)

    fun runInject(viewModel: ControllerModel)
    fun runInject(viewModel: DetailItemViewModel)
    fun runInject(viewModel: FMainModel)
    fun runInject(viewModel: FForpostModel)
    fun runInject(viewModel: FOctalModel)
    fun runInject(viewModel: FInformationModel)

    fun runInject(viewModel: SettingsModel)
    fun runInject(viewModel: DetailedDialogViewModel)
}