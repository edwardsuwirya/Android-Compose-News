package com.enigmacamp.newsCompose.ui.screens.article

import com.enigmacamp.newsCompose.model.Article

sealed class ArticleEvent {
    object LoadNext : ArticleEvent()
    object Refresh : ArticleEvent()
    data class ArticleSelected(val article: Article) : ArticleEvent()
}
