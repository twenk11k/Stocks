package com.twenk11k.stocks.di

import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.twenk11k.stocks.network.HttpRequestInterceptor
import com.twenk11k.stocks.network.StocksClient
import com.twenk11k.stocks.network.StocksService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://mobilechallenge.veripark.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideStocksService(retrofit: Retrofit): StocksService {
        return retrofit.create(StocksService::class.java)
    }

    @Provides
    @Singleton
    fun provideStocksClient(stocksService: StocksService): StocksClient {
        return StocksClient(stocksService)
    }



}