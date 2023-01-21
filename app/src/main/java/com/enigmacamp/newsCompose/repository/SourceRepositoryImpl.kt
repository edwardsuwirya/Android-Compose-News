package com.enigmacamp.newsCompose.repository

import com.enigmacamp.newsCompose.data.remote.NewsApi
import com.enigmacamp.newsCompose.model.Source
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(private val api: NewsApi) : SourceRepository {
    override suspend fun getAll(): Result<List<Source>> {
        return try {
            val response = api.getSources()
            if (response.isSuccessful) {
                response.body().let {
                    Result.success(response.body()!!)
                }
            } else {
                Result.success(emptyList())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun create(newSource: Source) {
//        repo.add(newSource)
        TODO()
    }
}