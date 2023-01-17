package com.enigmacamp.newsCompose.ui.screens.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun ArticleScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Text(text = "Article Screen")
    }
}