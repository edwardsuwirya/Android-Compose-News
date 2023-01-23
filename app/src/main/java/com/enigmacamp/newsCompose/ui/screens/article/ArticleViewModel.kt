package com.enigmacamp.newsCompose.ui.screens.article

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.newsCompose.common.DefaultPaginator
import com.enigmacamp.newsCompose.navigation.Navigator
import com.enigmacamp.newsCompose.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(articleRepository: ArticleRepository) : ViewModel() {
    private var _state = MutableStateFlow(ArticleUiState())
    val state = _state.asStateFlow()

    private val paginator = DefaultPaginator(
        initialKey = _state.value.page,
        onLoadUpdated = {
            _state.value = state.value.copy(isRefreshing = false, isLoading = it)
        },
        onRequest = { nextPage ->
            articleRepository.getAll(nextPage, 20)
        },
        getNextKey = {
            _state.value.page + 1
        },
        onError = {
            _state.value = _state.value.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            _state.value = _state.value.copy(
                articles = _state.value.articles + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    fun onEvent(event: ArticleEvent) {
        when (event) {
            is ArticleEvent.Refresh -> {
                Log.d("News", "Article Refresh")
                _state.update {
                    it.copy(
                        isRefreshing = true,
                        page = 0,
                        articles = emptyList(),
                    )
                }
                paginator.reset()
                onEvent(ArticleEvent.LoadNext)
            }
            is ArticleEvent.LoadNext -> viewModelScope.launch {
                paginator.loadNextItems()
            }
            is ArticleEvent.ArticleSelected -> {
                Navigator.navigateToArticleWebView("https://www.boltuix.com/")
            }
        }
    }

}