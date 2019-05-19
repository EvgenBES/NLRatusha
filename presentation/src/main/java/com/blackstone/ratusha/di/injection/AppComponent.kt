package com.blackstone.ratusha.di.injection


import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.screens.controller.ControllerModel
import com.blackstone.ratusha.ui.screens.detailed.DetailItemModel
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
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, DataModule::class, FragmentModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun runInject(controller: ControllerModel)
    fun runInject(detailItemModel: DetailItemModel)
    fun runInject(fMainModel: FMainModel)
    fun runInject(fForpostModel: FForpostModel)
    fun runInject(fOctalModel: FOctalModel)
    fun runInject(fInformationModel: FInformationModel)
    fun runInject(settingsModel: SettingsModel)
}