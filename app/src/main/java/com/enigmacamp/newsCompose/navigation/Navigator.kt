package com.enigmacamp.newsCompose.navigation

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow

object Navigator {
    var destination = MutableSharedFlow<String>(extraBufferCapacity = 1)
        private set

    fun navigate(newDestination: String) {
        Log.d("navigator", newDestination)
        this.destination.tryEmit(newDestination)
    }
}