package com.example.fox.ratusha.di.injection


import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.screens.forpost.FForpostPresenter
import com.example.fox.ratusha.ui.screens.main.FMainPresenter
import com.example.fox.ratusha.ui.screens.mainManager.MainPresenter
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

    fun runInject(mainPresenter: MainPresenter)
    fun runInject(fMainPresenter: FMainPresenter)
    fun runInject(fForpostPresenter: FForpostPresenter)
}