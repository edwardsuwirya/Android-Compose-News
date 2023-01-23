package com.enigmacamp.newsCompose.data.remote

import android.util.Log
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException

suspend fun <T> getResponse(request: suspend () -> Response<T>): T {
    return try {
        val result = request()

        if (result.isSuccessful) {
            result.body()!!
        } else {
            Log.d("News", "Utils: Response failed")
            throw Exception("Failed to get response")
        }
    } catch (e: ConnectException) {
        Log.d("News", "Utils: Client failed $e")
        throw IOException("Failed to connect")
    }
}