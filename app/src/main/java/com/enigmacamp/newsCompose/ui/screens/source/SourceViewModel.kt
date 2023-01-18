package com.enigmacamp.newsCompose.ui.screens.source

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.enigmacamp.newsCompose.navigation.Navigator
import com.enigmacamp.newsCompose.navigation.Screens
import com.enigmacamp.newsCompose.repository.Category
import com.enigmacamp.newsCompose.repository.SourceRepository
import com.enigmacamp.newsCompose.repository.SourceRepositoryImpl
import com.enigmacamp.newsCompose.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SourceViewModel(private val sourceRepository: SourceRepository) : ViewModel() {
    var sourceListResponse = mutableStateOf(SourceUiState(UiState.Init()))
        private set

    fun onEvent(event: SourceEvent) {
        when (event) {
            is SourceEvent.SourceList -> getSources()
            is SourceEvent.SourceListRefresh -> refresh()
            is SourceEvent.SourceSelected -> navigateToArticle(event.id)
        }
    }

    private fun navigateToArticle(sourceId: String) {
        Log.d("Source", sourceId)
        Navigator.navigate(Screens.Article(Category.General.name))
    }

    private fun getSources() {
        if (sourceListResponse.value.uiState.isInit == true) {

            viewModelScope.launch(Dispatchers.IO) {
                sourceListResponse.let {
                    it.value = it.value.copy(uiState = UiState.Loading)
                    try {
                        it.value = it.value.copy(
                            uiState = UiState.Success(sourceRepository.getAll()),
                        )
                    } catch (e: Exception) {
                        it.value = it.value.copy(uiState = UiState.Error(errorMessage = e.message))
                    }
                }
            }
        }
    }

    private fun refresh() {
        sourceListResponse.value = sourceListResponse.value.copy(
            uiState = UiState.Init()
        )
    }

    companion object factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SourceViewModel(SourceRepositoryImpl()) as T
        }
    }
}