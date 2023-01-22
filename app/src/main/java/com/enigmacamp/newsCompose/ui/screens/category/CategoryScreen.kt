package com.enigmacamp.newsCompose.ui.screens.category

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enigmacamp.newsCompose.repository.Category
import com.enigmacamp.newsCompose.ui.components.CategoryCard
import com.enigmacamp.newsCompose.ui.theme.SimplenewscomposeTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryScreen(viewModel: CategoryViewModel = hiltViewModel()) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Category") })
    }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Column(modifier = Modifier.weight(1f, true)) {
                    CategoryCard(category = Category.General, onClick = {
                        viewModel.onEvent(CategoryEvent.CategorySelected(Category.General))
                    })
                }
                Column(modifier = Modifier.weight(1f, true)) {
                    CategoryCard(
                        category = Category.Entertainment,
                        onClick = { viewModel.onEvent(CategoryEvent.CategorySelected(Category.Entertainment)) })
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Column(modifier = Modifier.weight(1f, true)) {
                    CategoryCard(
                        category = Category.Business,
                        onClick = { viewModel.onEvent(CategoryEvent.CategorySelected(Category.Business)) })
                }
                Column(modifier = Modifier.weight(1f, true)) {
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimplenewscomposeTheme {
        CategoryScreen(viewModel())
    }
}