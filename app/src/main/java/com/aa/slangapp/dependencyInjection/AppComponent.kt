package com.aa.slangapp.dependencyInjection

import android.app.Application
import com.aa.slangapp.App
import com.aa.slangapp.com.aa.slangapp.dependencyInjection.AppModule
import com.aa.slangapp.com.aa.slangapp.dependencyInjection.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}
