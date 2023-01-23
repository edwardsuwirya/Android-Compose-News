package com.enigmacamp.newsCompose.di


import com.enigmacamp.newsCompose.repository.SourceRepository
import com.enigmacamp.newsCompose.usecase.GetSourceListUseCase
import com.enigmacamp.newsCompose.usecase.getSourceList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGetSourceList(sourceRepository: SourceRepository): GetSourceListUseCase {
        return GetSourceListUseCase {
            getSourceList(it, sourceRepository)
        }
    }
}