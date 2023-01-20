package com.enigmacamp.newsCompose.ui.screens.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.newsCompose.common.UiState
import com.enigmacamp.newsCompose.navigation.Navigator
import com.enigmacamp.newsCompose.usecase.GetSourceListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(private val getSourceList: GetSourceListUseCase) :
    ViewModel() {
    private var _sourceState = MutableStateFlow(SourceUiState())
    val sourceState = _sourceState.asStateFlow()

    fun onEvent(event: SourceEvent) {
        when (event) {
            is SourceEvent.SourceList -> getSources()
            is SourceEvent.SourceListRefresh -> refresh()
            is SourceEvent.SourceSelected -> Navigator.navigateToArticle(event.id, event.name)
        }
    }

    private fun getSources() {
        if (sourceState.value.isInit) {
            getSourceList().onEach { res ->
                when (res) {
                    is UiState.Loading -> _sourceState.update {
                        it.copy(isLoading = true, isInit = false)
                    }
                    is UiState.Error -> _sourceState.update {
                        it.copy(
                            isLoading = false,
                            isInit = false,
                            error = res.errorMessage.toString()
                        )
                    }
                    is UiState.Success -> _sourceState.update {
                        it.copy(
                            isLoading = false,
                            isInit = false,
                            sources = res.data,
                        )
                    }
                }
            }.launchIn(viewModelScope)

        }
    }

    private fun refresh() {
        _sourceState.update {
            it.copy(isInit = true, error = "", sources = emptyList())
        }
    }
}