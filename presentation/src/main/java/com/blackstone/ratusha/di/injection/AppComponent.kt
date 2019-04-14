package com.blackstone.ratusha.di.injection


import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.screens.controller.ControllerViewModel
import com.blackstone.ratusha.ui.screens.detailed.DetailItemPresenter
import com.blackstone.ratusha.ui.screens.forpost.FForpostPresenter
import com.blackstone.ratusha.ui.screens.information.FInformationPresenter
import com.blackstone.ratusha.ui.screens.main.FMainPresenter
import com.blackstone.ratusha.ui.screens.octal.FOctalPresenter
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

    fun runInject(controller: ControllerViewModel)
    fun runInject(detailItemPresenter: DetailItemPresenter)
    fun runInject(fMainPresenter: FMainPresenter)
    fun runInject(fForpostPresenter: FForpostPresenter)
    fun runInject(fOctalPresenter: FOctalPresenter)
    fun runInject(fInformationPresenter: FInformationPresenter)
}