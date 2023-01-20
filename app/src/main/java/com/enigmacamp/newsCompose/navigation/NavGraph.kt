package com.enigmacamp.newsCompose.navigation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.enigmacamp.newsCompose.ui.screens.article.ArticleScreen
import com.enigmacamp.newsCompose.ui.screens.category.CategoryScreen
import com.enigmacamp.newsCompose.ui.screens.source.SourceScreen

@Composable
fun NavGraph(
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.Category.route
) {
    val dest = Navigator.destination
    LaunchedEffect(Unit) {
        dest.collect {
            Log.d("Nav", it)
            navController.navigate(it)
        }
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    )
    {
        composable(route = Screens.Category.route) {
            BackHandler {
                finishActivity()
            }
            CategoryScreen()
        }
        composable(route = "${Screens.Source.route}/{category}", arguments = listOf(
            navArgument("category") { type = NavType.StringType }
        )) {
            BackHandler {
                navController.navigateUp()
            }
            val arguments = requireNotNull(it.arguments)
            val currentCategory = arguments.getString("category", "")
            SourceScreen(category = currentCategory)
        }
        composable(route = "${Screens.Article.route}/{sourceId}/{sourceName}", arguments = listOf(
            navArgument("sourceId") { type = NavType.StringType },
            navArgument("sourceName") { type = NavType.StringType }
        )) {
            BackHandler {
                navController.navigateUp()
            }
            val arguments = requireNotNull(it.arguments)
            val currentSourceId = arguments.getString("sourceId", "")
            val currentSourceName = arguments.getString("sourceName", "")
            ArticleScreen(currentSourceId, currentSourceName)
        }
    }
}