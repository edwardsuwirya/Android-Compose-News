package com.enigmacamp.newsCompose.repository

import android.util.Log
import com.enigmacamp.newsCompose.model.Article
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor() : ArticleRepository {
    private val remoteDataSource = (1..100).map {
        Article(
            id = "$it",
            title = "Item $it",
            description = "Description $it"
        )
    }

    override suspend fun getAll(page: Int, pageSize: Int): Flow<Result<List<Article>>> = flow {
        Log.d("Paging", "Called $page")
        delay(1000L)
        val startingIndex = page * pageSize
        if (startingIndex + pageSize <= remoteDataSource.size) {
            emit(Result.success(remoteDataSource.slice(startingIndex until startingIndex + pageSize)))
        } else emit(Result.success(emptyList()))
    }
}