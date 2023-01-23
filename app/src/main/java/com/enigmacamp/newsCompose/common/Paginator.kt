package com.enigmacamp.newsCompose.common

interface Paginator<Key> {
    suspend fun loadNextItems()
    fun reset()
}