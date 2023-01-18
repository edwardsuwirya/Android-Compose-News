package com.enigmacamp.newsCompose.repository

import com.enigmacamp.newsCompose.model.Source
import kotlinx.coroutines.delay

class SourceRepositoryImpl : SourceRepository {
    private val repo = mutableListOf(Source("1", "ABC", "ABC"), Source("2", "DEF", "DEF"))
    override suspend fun getAll(): List<Source> {
        delay(1000L)
        return repo
    }

    override suspend fun create(newSource: Source) {
        repo.add(newSource)
    }
}