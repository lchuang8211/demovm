package com.example.demovm.dagger.viewModule

import androidx.lifecycle.ViewModel
import com.example.demovm.dagger.ViewModelBuilder
import com.example.demovm.dagger.ViewModelKey
import com.example.demovm.mainFunctionName.accountinfo.AccountInfoActivity
import com.example.demovm.mainFunctionName.accountinfo.AccountInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AccountInfoModule {

    @ContributesAndroidInjector( modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun accountInfoActivity(): AccountInfoActivity

    @Binds
    @IntoMap
    @ViewModelKey(AccountInfoViewModel::class)
    internal abstract fun bindViewModel(viewModel: AccountInfoViewModel): ViewModel
}