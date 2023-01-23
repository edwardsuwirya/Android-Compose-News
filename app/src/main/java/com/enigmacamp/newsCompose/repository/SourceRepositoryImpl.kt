package com.enigmacamp.newsCompose.repository

import android.util.Log
import com.enigmacamp.newsCompose.data.remote.NewsApi
import com.enigmacamp.newsCompose.data.remote.getResponse
import com.enigmacamp.newsCompose.model.Source
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(private val api: NewsApi) : SourceRepository {
    override suspend fun getAll() = flow {
//        emit(
//            Result.success(
//                listOf(
//                    Source(
//                        id = "DUMMY",
//                        title = "Dummy Cache",
//                        description = "To simulate data from DB"
//                    )
//                )
//            )
//        )
//        emit(Result.failure(Exception("Corrupt")))
        val result = getResponse { api.getSources() }
        emit(Result.success(result))
    }
        .retryWhen { cause, attempt ->
            if (cause is IOException) {
                delay(3000)
                val res = attempt < 3
                Log.d("News", "Repo: Retru - $attempt - $res")
                res
            } else {
                false
            }
        }
        .catch {
            emit(Result.failure(it))
        }

    override suspend fun create(newSource: Source) {
//        repo.add(newSource)
        TODO()
    }
}

