package com.example.demovm.dagger.viewModule

import androidx.lifecycle.ViewModel
import com.example.demovm.dagger.ViewModelBuilder
import com.example.demovm.dagger.ViewModelKey
import com.example.demovm.mainFunctionName.number.one.OneFragment
import com.example.demovm.mainFunctionName.number.one.OneFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap



@Module
abstract class OneFragmentModule {

    @ContributesAndroidInjector(modules=[
        ViewModelBuilder::class
    ])
    internal abstract fun OneFragment(): OneFragment

    @Binds
    @IntoMap
    @ViewModelKey(OneFragmentViewModel::class)
    internal abstract fun bindViewModel(viewModel: OneFragmentViewModel): ViewModel
}