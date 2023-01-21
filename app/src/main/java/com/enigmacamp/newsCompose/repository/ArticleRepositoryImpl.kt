package com.enigmacamp.newsCompose.repository

import android.util.Log
import com.enigmacamp.newsCompose.model.Article
import kotlinx.coroutines.delay
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor() : ArticleRepository {
    private val remoteDataSource = (1..100).map {
        Article(
            id = "$it",
            title = "Item $it",
            description = "Description $it"
        )
    }

    override suspend fun getAll(page: Int, pageSize: Int): Result<List<Article>> {
        Log.d("Paging", "Called $page")
        delay(1000L)
        val startingIndex = page * pageSize
        return if (startingIndex + pageSize <= remoteDataSource.size) {
            Result.success(remoteDataSource.slice(startingIndex until startingIndex + pageSize))
        } else Result.success(emptyList())
    }
}