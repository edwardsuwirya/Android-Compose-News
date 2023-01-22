package com.enigmacamp.newsCompose.data.remote

import retrofit2.Response

suspend fun <T> getResponse(request: suspend () -> Response<T>): Result<T> {
    return try {
        val result = request()

        if (result.isSuccessful) {
            Result.success(result.body()!!)
        } else {
            Result.failure(Exception("Fail to get response"))
        }
    } catch (e: Throwable) {
        Result.failure(e)
    }
}