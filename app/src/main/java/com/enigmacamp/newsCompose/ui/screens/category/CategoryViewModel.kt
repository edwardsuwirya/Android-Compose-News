package com.enigmacamp.newsCompose.ui.screens.category

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.enigmacamp.newsCompose.navigation.Navigator
import com.enigmacamp.newsCompose.navigation.Screens
import com.enigmacamp.newsCompose.repository.Category

class CategoryViewModel : ViewModel() {
    var state by mutableStateOf(CategoryState(Category.General))
        private set

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.CategorySelected -> {
                val category = event.category
                state = state.copy(category = category)
                when (category) {
                    is Category.General -> {
                        Navigator.navigate(Screens.Source(Category.General.name))
                    }
                    is Category.Entertainment -> {
                        Navigator.navigate(Screens.Source(Category.Entertainment.name))
                    }
                    is Category.Business -> {
                        Navigator.navigate(Screens.Source((Category.Business.name)))
                    }
                }

            }

        }
    }
}