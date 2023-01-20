package com.enigmacamp.newsCompose.ui.screens.source

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enigmacamp.newsCompose.model.Source
import com.enigmacamp.newsCompose.ui.components.Loading
import com.enigmacamp.newsCompose.ui.components.SourceCard
import com.enigmacamp.newsCompose.utils.UiState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SourceScreen(
    viewModel: SourceViewModel = viewModel(factory = SourceViewModel.VMFactory),
    category: String
) {
    val state = viewModel.sourceState.collectAsState().value
    val s = rememberScaffoldState()

    with(state) {
        if (uiState is UiState.Error) {
            LaunchedEffect(Unit) {
                s.snackbarHostState.showSnackbar(
                    message = "Error",
                    duration = SnackbarDuration.Short
                )
            }
        }

        LaunchedEffect(uiState.isInit) {
            viewModel.onEvent(SourceEvent.SourceList)
        }
        Scaffold(scaffoldState = s) {
            Content(
                category = category,
                state = uiState,
                onEvent = viewModel::onEvent
            )
        }
    }
}

@Composable
fun Content(category: String, state: UiState<List<Source>>, onEvent: (SourceEvent) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(text = "Source Screen - $category")
            Button(onClick = { onEvent(SourceEvent.SourceListRefresh) }) {
                Text(text = "Refresh")
            }
            when (state) {
                is UiState.Success -> {
                    if (state.data.isNullOrEmpty()) {
                        Text(text = "No data")
                    } else {
                        val list: List<Source> = state.data
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
                is UiState.Error -> {
                    Text(text = "Error")
                }
                else -> {}
            }
        }
        if (state is UiState.Loading) {
            Loading()
        }

    }
}