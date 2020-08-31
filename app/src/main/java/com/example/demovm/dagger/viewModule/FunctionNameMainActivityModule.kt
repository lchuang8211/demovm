package com.example.demovm.dagger.viewModule

import androidx.lifecycle.ViewModel
import com.example.demovm.mainFunctionName.FunctionNameMainActivity
import com.example.demovm.mainFunctionName.FunctionNameMainActivityViewModel
import com.example.demovm.dagger.ViewModelBuilder
import com.example.demovm.dagger.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
@Module
abstract class FunctionNameMainActivityModule {

    // 將 ViewModelFactory 注入指定 Activity 當中，實現初始化
    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun functionNameMainActivity(): FunctionNameMainActivity

    // 將
    @Binds
    @IntoMap
    @ViewModelKey(FunctionNameMainActivityViewModel::class)
    internal abstract fun bindViewModel(viewModel: FunctionNameMainActivityViewModel): ViewModel

}