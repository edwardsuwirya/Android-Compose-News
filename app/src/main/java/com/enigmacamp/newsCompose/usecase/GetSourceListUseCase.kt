package com.enigmacamp.newsCompose.usecase

import com.enigmacamp.newsCompose.common.UiState
import com.enigmacamp.newsCompose.model.Source
import com.enigmacamp.newsCompose.repository.SourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSourceListUseCase(private val repo: SourceRepository) {
    operator fun invoke(): Flow<UiState<List<Source>>> = flow {
        try {
            emit(UiState.Loading)
            val result = repo.getAll()
            emit(UiState.Success(result))

        } catch (e: Exception) {
            emit(UiState.Error(e.message))
        }
    }

}