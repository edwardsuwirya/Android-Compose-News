package com.enigmacamp.newsCompose.navigation

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow

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
}