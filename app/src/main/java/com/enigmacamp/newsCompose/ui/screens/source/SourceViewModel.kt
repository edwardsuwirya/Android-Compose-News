package com.enigmacamp.newsCompose.ui.screens.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.newsCompose.common.UiState
import com.enigmacamp.newsCompose.navigation.Navigator
import com.enigmacamp.newsCompose.usecase.GetSourceListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(private val getSourceList: GetSourceListUseCase) :
    ViewModel() {
    private var _sourceState = MutableStateFlow(SourceUiState(UiState.Init()))
    val sourceState = _sourceState.asStateFlow()

    fun onEvent(event: SourceEvent) {
        when (event) {
            is SourceEvent.SourceList -> getSources()
            is SourceEvent.SourceListRefresh -> refresh()
            is SourceEvent.SourceSelected -> Navigator.navigateToArticle(event.id, event.name)
            else -> {}
        }
    }

    private fun getSources() {
        if (sourceState.value.uiState.isInit == true) {
            viewModelScope.launch(Dispatchers.IO) {
                _sourceState.update {
                    it.copy(uiState = UiState.Loading)
                }
                try {
                    _sourceState.update {
                        it.copy(
                            uiState = UiState.Success(getSourceList()),
                        )
                    }
                } catch (e: Exception) {
                    _sourceState.update {
                        it.copy(uiState = UiState.Error(errorMessage = e.message))
                    }
                }
            }
        }
    }

    private fun refresh() {
        _sourceState.update {
            it.copy(uiState = UiState.Init())
        }
    }
}