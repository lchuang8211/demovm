package com.example.demovm.dagger.viewModule

import androidx.lifecycle.ViewModel
import com.example.demovm.bottomdraw.BottomDrawFragment
import com.example.demovm.bottomdraw.BottomDrawTabViewModel
import com.example.demovm.dagger.ViewModelBuilder
import com.example.demovm.dagger.ViewModelKey
import com.example.demovm.mainFunctionName.FunctionMainFragment
import com.example.demovm.mainFunctionName.FunctionMainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class BottomDrawModule {

    @ContributesAndroidInjector(modules=[
        ViewModelBuilder::class
    ])
    internal abstract fun BottomDrawFragment(): BottomDrawFragment

    @Binds
    @IntoMap
    @ViewModelKey(BottomDrawTabViewModel::class)
    internal abstract fun bindViewModel(viewModel: BottomDrawTabViewModel): ViewModel

}