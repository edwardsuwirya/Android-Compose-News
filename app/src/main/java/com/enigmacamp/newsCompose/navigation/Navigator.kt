package com.enigmacamp.newsCompose.navigation

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object Navigator {
    var destination = MutableSharedFlow<String>(extraBufferCapacity = 1)
        private set

    private fun navigate(newDestination: String) {
        Log.d("navigator", newDestination)
        this.destination.tryEmit(newDestination)
    }

    fun navigateToSource(categoryName: String) {
        navigate(Screens.Source(categoryName))
    }

    fun navigateToArticle(sourceId: String, sourceName: String) {
        navigate(Screens.Article(sourceId, sourceName))
    }

    fun navigateToArticleWebView(url: String) {
        val urlEncoded = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
        Log.d("Url", urlEncoded)
        navigate(Screens.ArticleWebView(urlEncoded))
    }
}