package com.enigmacamp.newsCompose.di


import com.enigmacamp.newsCompose.data.remote.NewsApi
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
    fun provideSourceRepository(api: NewsApi): SourceRepository {
        return SourceRepositoryImpl(api)
    }
}