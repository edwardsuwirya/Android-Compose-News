package com.enigmacamp.newsCompose.di


import com.enigmacamp.newsCompose.repository.ArticleRepository
import com.enigmacamp.newsCompose.repository.ArticleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ArticleModule {
    @Provides
    fun provideArticleRepository(): ArticleRepository {
        return ArticleRepositoryImpl()
    }
}