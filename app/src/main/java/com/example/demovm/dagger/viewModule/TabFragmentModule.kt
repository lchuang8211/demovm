package com.example.demovm.dagger.viewModule

import androidx.lifecycle.ViewModel
import com.example.demovm.dagger.ViewModelBuilder
import com.example.demovm.dagger.ViewModelKey
import com.example.demovm.mainFunctionName.tabFragment.TabFragment
import com.example.demovm.mainFunctionName.tabFragment.TabFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class TabFragmentModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun TabFragment() : TabFragment

    @Binds
    @IntoMap
    @ViewModelKey(TabFragmentViewModel::class)
    internal abstract fun bindViewModel(viewModel: TabFragmentViewModel): ViewModel
}