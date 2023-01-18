package com.enigmacamp.newsCompose.ui.screens.source

sealed class SourceEvent {
    object SourceList : SourceEvent()
    object SourceListRefresh : SourceEvent()
    data class SourceSelected(val id: String) : SourceEvent()
}
