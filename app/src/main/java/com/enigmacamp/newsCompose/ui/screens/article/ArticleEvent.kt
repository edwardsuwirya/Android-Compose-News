package com.enigmacamp.newsCompose.ui.screens.article

sealed class ArticleEvent {
    object LoadNext : ArticleEvent()
    object Refresh : ArticleEvent()
    data class ArticleSelected(val id: String) : ArticleEvent()
}
