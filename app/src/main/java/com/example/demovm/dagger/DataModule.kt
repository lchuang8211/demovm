package com.example.demovm.dagger

import com.example.demovm.domain.fruit.AppleUseCase
import com.example.demovm.data.repository.fruit.FruitInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// 存放所有 UseCase
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideAppleUseCase(repository: FruitInteractor): AppleUseCase {
        return AppleUseCase(repository)
    }

    /**
    *  other UseCase function
    * */
}
