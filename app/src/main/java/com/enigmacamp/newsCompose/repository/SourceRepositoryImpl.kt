package com.enigmacamp.newsCompose.repository

import com.enigmacamp.newsCompose.data.remote.NewsApi
import com.enigmacamp.newsCompose.data.remote.getResponse
import com.enigmacamp.newsCompose.model.Source
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(private val api: NewsApi) : SourceRepository {
    override suspend fun getAll(): Result<List<Source>> {
        return getResponse { api.getSources() }
    }

    override suspend fun create(newSource: Source) {
//        repo.add(newSource)
        TODO()
    }
}