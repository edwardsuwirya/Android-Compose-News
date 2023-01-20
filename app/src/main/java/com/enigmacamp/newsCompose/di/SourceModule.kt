package com.enigmacamp.newsCompose.di


import com.enigmacamp.newsCompose.repository.SourceRepository
import com.enigmacamp.newsCompose.repository.SourceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {
    @Provides
    fun provideSourceRepository():SourceRepository {
        return SourceRepositoryImpl()
    }
}