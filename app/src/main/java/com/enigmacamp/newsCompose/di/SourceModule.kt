package com.enigmacamp.newsCompose.di


import com.enigmacamp.newsCompose.data.remote.NewsApi
import com.enigmacamp.newsCompose.repository.SourceRepository
import com.enigmacamp.newsCompose.repository.SourceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class SourceModule {
    @Provides
    @ViewModelScoped
    fun provideSourceRepository(api: NewsApi): SourceRepository {
        return SourceRepositoryImpl(api)
    }
}