package com.enigmacamp.newsCompose.ui.screens.article

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.newsCompose.common.DefaultPaginator
import com.enigmacamp.newsCompose.common.DispatcherProvider
import com.enigmacamp.newsCompose.navigation.Navigator
import com.enigmacamp.newsCompose.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    articleRepository: ArticleRepository,
    dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private var _state = MutableStateFlow(ArticleUiState())
    val state = _state.asStateFlow()

    private val paginator = DefaultPaginator(
        initialKey = _state.value.page,
        onRequest = { page, makingRequest, nextKey ->
            articleRepository.getAll(page, 20).onEach { res ->
                res.fold(onSuccess = {
                    val nextPage = _state.value.page + 1
                    _state.update { s ->
                        s.copy(
                            articles = _state.value.articles + it,
                            endReached = it.isEmpty(),
                            page = nextPage,
                            isRefreshing = false,
                            isLoading = false
                        )
                    }
                    makingRequest(false).also {
                        nextKey(nextPage)
                    }
                }, onFailure = {
                    _state.update { s ->
                        s.copy(error = it.localizedMessage)
                    }
                })
            }.flowOn(dispatcherProvider.io)
                .launchIn(viewModelScope)
        },
        onLoadUpdated = {
            _state.update { s ->
                s.copy(
                    isRefreshing = false,
                    isLoading = it
                )
            }
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