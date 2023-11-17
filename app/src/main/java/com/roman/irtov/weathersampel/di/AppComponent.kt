package com.roman.irtov.weathersampel.di

import android.content.Context
import com.roman.irtov.weathersampel.di.module.ViewModelModule
import com.roman.irtov.weathersampel.di.module.network.NetworkModule
import com.roman.irtov.weathersampel.di.module.repository.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AppProvider{

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder

    }

    companion object {

        private var component: AppComponent? = null

        fun init(context: Context) {
            component ?: DaggerAppComponent.builder()
                .context(context)
                .build().apply {
                    component = this
                }
        }

        fun get(): AppComponent {
            return component ?: throw NotImplementedError("You should call 'init' method")
        }
    }


}
