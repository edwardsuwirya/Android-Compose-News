package com.enigmacamp.newsCompose.repository

import com.enigmacamp.newsCompose.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getAll(page: Int, pageSize: Int): Flow<Result<List<Article>>>
}