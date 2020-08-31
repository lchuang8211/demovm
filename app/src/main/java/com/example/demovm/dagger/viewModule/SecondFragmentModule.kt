package com.example.demovm.dagger.viewModule

import androidx.lifecycle.ViewModel
import com.example.demovm.dagger.ViewModelBuilder
import com.example.demovm.dagger.ViewModelKey

import com.example.demovm.mainFunctionName.subFunctionNameTwo.SecondFragment
import com.example.demovm.mainFunctionName.subFunctionNameTwo.SecondFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SecondFragmentModule {

    @ContributesAndroidInjector(modules=[
        ViewModelBuilder::class
    ])
    internal abstract fun SecondFragment(): SecondFragment

    @Binds
    @IntoMap
    @ViewModelKey(SecondFragmentViewModel::class)
    internal abstract fun bindViewModel(viewModel: SecondFragmentViewModel): ViewModel
}