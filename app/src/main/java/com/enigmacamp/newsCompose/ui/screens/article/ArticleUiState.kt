package com.enigmacamp.newsCompose.ui.screens.article

import com.enigmacamp.newsCompose.model.Article

data class ArticleUiState(
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)

