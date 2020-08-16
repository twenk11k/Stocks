package com.twenk11k.stocks.di

import com.twenk11k.stocks.network.StocksClient
import com.twenk11k.stocks.repository.StocksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {


    @Provides
    @ActivityRetainedScoped
    fun proviceStocksRepository(stocksClient: StocksClient): StocksRepository {
        return StocksRepository(stocksClient)
    }

}