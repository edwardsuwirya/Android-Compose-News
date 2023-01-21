package com.enigmacamp.newsCompose.repository

import com.enigmacamp.newsCompose.model.Article

interface ArticleRepository {
    suspend fun getAll(page: Int, pageSize: Int): Result<List<Article>>
}