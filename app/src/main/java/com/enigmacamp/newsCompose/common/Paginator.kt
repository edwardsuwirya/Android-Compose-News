package com.enigmacamp.newsCompose.common

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}