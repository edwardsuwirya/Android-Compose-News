package com.enigmacamp.newsCompose.ui.screens.source

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.newsCompose.common.DispatcherProvider
import com.enigmacamp.newsCompose.navigation.Navigator
import com.enigmacamp.newsCompose.usecase.GetSourceListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(
    private val getSourceList: GetSourceListUseCase,
    private val dispatchers: DispatcherProvider
) :
    ViewModel() {
    private var _sourceState = MutableStateFlow(SourceUiState())
    val sourceState = _sourceState.asStateFlow()

    fun onEvent(event: SourceEvent) {
        when (event) {
            is SourceEvent.SourceList -> getSources()
            is SourceEvent.SourceListRefresh -> refresh()
            is SourceEvent.SourceSelected -> Navigator.navigateToArticle(event.id, event.name)
            is SourceEvent.Dismissed -> _sourceState.update {
                it.copy(error = "")
            }
        }
    }

    private fun getSources() {
        if (sourceState.value.isInit) {
//            viewModelScope.launch {
            Log.d("News", "VM: Exec")
            getSourceList("m")
                .onStart {
                    if (_sourceState.value.isRefreshing) {
                        _sourceState.update {
                            it.copy(
                                isInit = false,
                                isLoading = false,
                                error = "",
                                sources = emptyList()
                            )
                        }
                    } else {
                        _sourceState.update {
                            it.copy(
                                isInit = false,
                                isRefreshing = false,
                                isLoading = true,
                                error = "",
                                sources = emptyList()
                            )
                        }
                    }

                }
                .onEach { res ->
                    Log.d("News", "VM: Each")
                    res.fold(onFailure = { e ->
                        if (e is IOException) {
                            _sourceState.update {
                                it.copy(
                                    isLoading = false,
                                    isRefreshing = false,
                                    isInit = false,
                                    error = ""
                                )
                            }
                        } else {
                            _sourceState.update {
                                it.copy(
                                    isLoading = false,
                                    isRefreshing = false,
                                    isInit = false,
                                    error = e.message.toString()
                                )
                            }
                        }
                    }, onSuccess = { data ->
                        Log.d("News", "VM: ${data.toString()}")
                        _sourceState.update {
                            it.copy(
                                isLoading = false,
                                isRefreshing = false,
                                isInit = false,
                                sources = data,
                            )
                        }
                    }
                    )
                }
                .flowOn(dispatchers.io)
                .launchIn(viewModelScope)
        }
    }

    private fun refresh() {
        _sourceState.update {
            it.copy(
                isRefreshing = true,
                isLoading = false,
                isInit = true,
                error = "",
                sources = emptyList()
            )
        }
    }
}