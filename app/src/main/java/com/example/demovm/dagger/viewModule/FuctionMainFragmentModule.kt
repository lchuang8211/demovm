package com.example.demovm.dagger.viewModule

import androidx.lifecycle.ViewModel
import com.example.demovm.dagger.ViewModelBuilder
import com.example.demovm.dagger.ViewModelKey
import com.example.demovm.mainFunctionName.FunctionMainFragment
import com.example.demovm.mainFunctionName.FunctionMainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FuctionMainFragmentModule {


        @ContributesAndroidInjector(modules=[
            ViewModelBuilder::class
        ])
        internal abstract fun FunctionMainFragment(): FunctionMainFragment

        @Binds
        @IntoMap
        @ViewModelKey(FunctionMainFragmentViewModel::class)
        internal abstract fun bindViewModel(viewModel: FunctionMainFragmentViewModel): ViewModel

}