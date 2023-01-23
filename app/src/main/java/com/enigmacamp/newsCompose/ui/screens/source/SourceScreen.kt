package com.enigmacamp.newsCompose.ui.screens.source

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enigmacamp.newsCompose.ui.components.SourceCard
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SourceScreen(
    viewModel: SourceViewModel = hiltViewModel(),
    category: String
) {
    val state = viewModel.sourceState.collectAsState().value
    val scaffoldState = rememberScaffoldState()

    if (state.error.isNotEmpty()) {
        LaunchedEffect(scaffoldState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Error",
                duration = SnackbarDuration.Short
            ).also {
                when (it) {
                    SnackbarResult.Dismissed -> viewModel.onEvent(SourceEvent.Dismissed)
                    else -> {}
                }
            }
        }
    }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)

    LaunchedEffect(state.isInit) {
        viewModel.onEvent(SourceEvent.SourceList)
    }
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = category.uppercase()) })
    }, scaffoldState = scaffoldState) {
        Content(
            state = state,
            swipeState = swipeRefreshState,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
fun Content(state: SourceUiState, swipeState: SwipeRefreshState, onEvent: (SourceEvent) -> Unit) {
    SwipeRefresh(
        state = swipeState,
        indicator = { indicatorState, refreshTrigger ->
            SwipeRefreshIndicator(state = indicatorState, refreshTriggerDistance = refreshTrigger)
        },
        onRefresh = { onEvent(SourceEvent.SourceListRefresh) }) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(4.dp)) {
                val list = state.sources
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    if (state.sources.isEmpty()) {
                        item {
                            Text(text = "No Data")
                        }
                    } else {
                        items(list) { s ->
                            SourceCard(s) {
                                onEvent(SourceEvent.SourceSelected(it.id, it.title))
                            }
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

}