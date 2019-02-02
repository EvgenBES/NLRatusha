package com.example.fox.ratusha.di.injection


import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.main.MainPresenter
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun runInject(mainPresenter: MainPresenter)
}