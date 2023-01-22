package com.enigmacamp.newsCompose.di


import com.enigmacamp.newsCompose.repository.ArticleRepository
import com.enigmacamp.newsCompose.repository.ArticleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ArticleModule {
    @Provides
    @ViewModelScoped
    fun provideArticleRepository(): ArticleRepository {
        return ArticleRepositoryImpl()
    }
}