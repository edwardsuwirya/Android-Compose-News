package com.enigmacamp.newsCompose.repository

import com.enigmacamp.newsCompose.model.Source
import kotlinx.coroutines.flow.Flow

interface SourceRepository {
    suspend fun getAll(): Flow<Result<List<Source>>>
    suspend fun create(newSource: Source)
}