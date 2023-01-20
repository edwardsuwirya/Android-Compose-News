package com.enigmacamp.newsCompose.ui.screens.source

import com.enigmacamp.newsCompose.model.Source


data class SourceUiState(
    val isInit: Boolean = true,
    val sources: List<Source> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
