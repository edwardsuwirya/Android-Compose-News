package com.enigmacamp.newsCompose.repository

import com.enigmacamp.newsCompose.data.remote.NewsApi
import com.enigmacamp.newsCompose.model.Source
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(private val api: NewsApi) : SourceRepository {
    override suspend fun getAll(): List<Source> {
        try {
            val response = api.getSources()
            if (response.isSuccessful) {
                response.body().let {
                    return response.body()!!
                }
            } else {
                throw Exception("Request can not be processed")
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun create(newSource: Source) {
//        repo.add(newSource)
        TODO()
    }
}