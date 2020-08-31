package com.example.demovm.dagger

import android.content.Context
import com.example.demovm.dagger.viewModule.*
import com.example.demovm.mainFunctionName.HHHApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


// Component 提供所要使用的 Module 給 APP 使用
// Singleton 避免建立多個實體 僅能一次 (上下層一至)
@Singleton
@Component(
    modules = [
        DataModule::class,
        RepositoryModule::class,
        ApplicationModule::class,
        RoomDataBaseModule::class,
        AndroidSupportInjectionModule::class,
        ApiModule::class,
        MainFragmentModule::class,
        SecondFragmentModule::class,
        FunctionNameMainActivityModule::class,
        FuctionMainFragmentModule::class,
        TabFragmentModule::class,
        AccountInfoModule::class,
        AccountInfoFragmentModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<HHHApplication> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance applicationContext: Context): ApplicationComponent

    }


}