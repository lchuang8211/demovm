package com.example.demovm.dagger

import com.example.demovm.data.repository.fruit.FruitInteractor
import com.example.demovm.data.repository.fruit.FruitRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// 存放所有 Repository
@Module
object RepositoryModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideFruitRepository(): FruitInteractor {
        return FruitRepository()
    }

    /**
     *  other Repository function
     * */

}