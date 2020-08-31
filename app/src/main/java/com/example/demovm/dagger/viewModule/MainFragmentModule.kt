package com.example.demovm.dagger.viewModule

import androidx.lifecycle.ViewModel
import com.example.demovm.dagger.ViewModelBuilder
import com.example.demovm.dagger.ViewModelKey
import com.example.demovm.mainFunctionName.subFunctionNameOne.MainFragment
import com.example.demovm.mainFunctionName.subFunctionNameOne.MainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector(modules=[
        ViewModelBuilder::class
    ])
    internal abstract fun MainFragment(): MainFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    internal abstract fun bindViewModel(viewModel: MainFragmentViewModel): ViewModel
}