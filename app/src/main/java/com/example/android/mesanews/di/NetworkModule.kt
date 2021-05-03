package com.example.android.mesanews.di

import com.example.android.mesanews.api.MesaNewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideMesaNewsService(): MesaNewsService {
        return MesaNewsService.create()
    }
}