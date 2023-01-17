package com.enigmacamp.newsCompose.ui.screens.category

import com.enigmacamp.newsCompose.repository.Category

sealed class CategoryEvent {
    data class CategorySelected(val category: Category) : CategoryEvent()
}
