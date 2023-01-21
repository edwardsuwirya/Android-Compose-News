package com.enigmacamp.newsCompose.repository

import com.enigmacamp.newsCompose.model.Source

interface SourceRepository {
    suspend fun getAll(): Result<List<Source>>
    suspend fun create(newSource: Source)
}