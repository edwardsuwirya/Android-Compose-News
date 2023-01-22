package com.enigmacamp.newsCompose.ui.screens.article

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticleScreen(
    sourceId: String,
    sourceName: String,
    viewModel: ArticleViewModel = hiltViewModel()
) {


    val articleState = viewModel.state.collectAsState()
    val state = articleState.value

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)

    LaunchedEffect(Unit) {
        Log.d("Paging", "Effect")
        viewModel.onEvent(ArticleEvent.LoadNext)
    }
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Article From $sourceName") })
    }) {
        Content(
            state = state,
            swipeState = swipeRefreshState,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
fun Content(state: ArticleUiState, swipeState: SwipeRefreshState, onEvent: (ArticleEvent) -> Unit) {
    SwipeRefresh(
        state = swipeState,
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(state = state, refreshTriggerDistance = refreshTrigger)
        },
        onRefresh = { onEvent(ArticleEvent.Refresh) }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                items(state.articles.size) { i ->
//                Log.d("Paging", "Lazy $i - size ${state.articles.size}")
                    val item = state.articles[i]
                    if (i >= state.articles.size - 1 && !state.endReached && !state.isLoading) {
                        onEvent(ArticleEvent.LoadNext)
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable { onEvent(ArticleEvent.ArticleSelected(item)) }
                    ) {
                        Text(
                            text = item.title,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(item.description)
                    }
                }
                item {
                    if (state.isLoading) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }

        }

    }
}