package com.enigmacamp.newsCompose.ui.screens.category

import androidx.lifecycle.ViewModel
import com.enigmacamp.newsCompose.navigation.Navigator
import com.enigmacamp.newsCompose.repository.Category

class CategoryViewModel : ViewModel() {
    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.CategorySelected -> {
                when (event.category) {
                    is Category.General -> Navigator.navigateToSource(Category.General.name)
                    is Category.Entertainment -> Navigator.navigateToSource(Category.Entertainment.name)
                    is Category.Business -> Navigator.navigateToSource(Category.Business.name)
                }
            }
        }
    }
}