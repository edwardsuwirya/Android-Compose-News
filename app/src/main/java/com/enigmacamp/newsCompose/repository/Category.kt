package com.enigmacamp.newsCompose.repository

sealed class Category(val name: String) {
    object General : Category("general")
    object Entertainment : Category("entertainment")
    object Business : Category("business")
}
