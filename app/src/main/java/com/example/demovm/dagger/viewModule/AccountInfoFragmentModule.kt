package com.example.demovm.dagger.viewModule

import androidx.lifecycle.ViewModel
import com.example.demovm.dagger.ViewModelBuilder
import com.example.demovm.dagger.ViewModelKey
import com.example.demovm.mainFunctionName.accountinfo.AccountInfoFragment
import com.example.demovm.mainFunctionName.accountinfo.AccountInfoFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AccountInfoFragmentModule {

    @ContributesAndroidInjector( modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun accountInfoFragment(): AccountInfoFragment

    @Binds
    @IntoMap
    @ViewModelKey(AccountInfoFragmentViewModel::class)
    internal abstract fun bindViewModel(viewModel: AccountInfoFragmentViewModel): ViewModel
}