package com.enigmacamp.newsCompose.di


import com.enigmacamp.newsCompose.repository.SourceRepository
import com.enigmacamp.newsCompose.usecase.GetSourceListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideGetSourceList(sourceRepository: SourceRepository): GetSourceListUseCase {
        return GetSourceListUseCase(sourceRepository)
    }
}