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
import com.enigmacamp.newsCompose.ui.components.Loading
import com.enigmacamp.newsCompose.ui.components.SourceCard

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

    LaunchedEffect(state.isInit) {
        viewModel.onEvent(SourceEvent.SourceList)
    }
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = category.uppercase()) })
    }, scaffoldState = scaffoldState) {
        Content(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
fun Content(state: SourceUiState, onEvent: (SourceEvent) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(4.dp)) {
            Button(onClick = { onEvent(SourceEvent.SourceListRefresh) }) {
                Text(text = "Refresh")
            }
            if (state.sources.isEmpty()) {
                Text(text = "No data")
            } else {
                val list = state.sources
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(list) { s ->
                        SourceCard(s) {
                            onEvent(SourceEvent.SourceSelected(it.id, it.title))
                        }
                    }
                }
            }
        }
        if (state.isLoading) {
            Loading()
        }

    }
}