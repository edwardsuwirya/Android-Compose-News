package com.enigmacamp.newsCompose.navigation


sealed class Screens(val route: String) {
    object Category : Screens("category")
    object Source : Screens("source") {
        operator fun invoke(category: String) = route.appendParams(
            "category" to category
        )
    }

    object Article : Screens("article") {
        operator fun invoke(sourceId: String, sourceName: String) = route.appendParams(
            "sourceId" to sourceId, "sourceName" to sourceName
        )
    }
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