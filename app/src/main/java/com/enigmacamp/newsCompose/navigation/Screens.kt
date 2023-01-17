package com.enigmacamp.newsCompose.navigation


sealed class Screens(val route: String, vararg params: String) {
    object Category : Screens("category")
    object Source : Screens("source", "category") {
        operator fun invoke(category: String) = route.appendParams(
            "category" to category
        )
    }

    object Article : Screens("article")
}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}