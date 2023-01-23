package com.enigmacamp.newsCompose.usecase

import com.enigmacamp.newsCompose.model.Source
import com.enigmacamp.newsCompose.repository.SourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

fun interface GetSourceListUseCase : (String) -> Flow<Result<List<Source>>>

fun getSourceList(word: String, repo: SourceRepository): Flow<Result<List<Source>>> = flow {
//    repo.getAll().map {
//        it.fold(onSuccess = { res ->
//            return@map res
//        }, onFailure = { e ->
//            throw e
//        })
//    }.onEach {
//        val res = mutableListOf<Source>()
//        it.forEach { s ->
//            if (s.title.startsWith(word, true)) {
//                res.add(s)
//            }
//        }
//        emit(Result.success(res))
//    }.catch {
//        Log.d("News", "UseCase: Error $it")
//        emit(Result.failure(it))
//    }
//        .collect()
    repo.getAll().onEach { emit(it) }.collect()
}

