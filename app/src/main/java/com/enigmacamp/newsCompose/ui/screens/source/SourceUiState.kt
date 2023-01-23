package com.enigmacamp.newsCompose.ui.screens.source

import com.enigmacamp.newsCompose.model.Source


data class SourceUiState(
    val isRefreshing: Boolean = false,
    val isInit: Boolean = true,
    val sources: List<Source> = emptyList(),
    val isLoading: Boolean = true,
    val error: String = ""
)
