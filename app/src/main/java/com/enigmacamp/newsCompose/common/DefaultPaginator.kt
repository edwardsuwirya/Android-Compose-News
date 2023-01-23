package com.enigmacamp.newsCompose.common

class DefaultPaginator<Key>(
    private val initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Key, makingRequest: (Boolean) -> Unit, getNextKey: (Key) -> Unit) -> Unit,
) : Paginator<Key> {
    private var currentKey = initialKey
    var isMakingRequest = false


    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        toggleMakingRequest(true)
        onLoadUpdated(true)
        onRequest(currentKey, ::toggleMakingRequest, ::getNextKey)
    }

    private fun getNextKey(newKey: Key) {
        currentKey = newKey
    }

    private fun toggleMakingRequest(isRequesting: Boolean) {
        isMakingRequest = isRequesting
    }

    override fun reset() {
        currentKey = initialKey
    }
}