package com.enigmacamp.newsCompose.data.remote

import com.enigmacamp.newsCompose.model.Source
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("sources")
    suspend fun getSources(): Response<List<Source>>
}