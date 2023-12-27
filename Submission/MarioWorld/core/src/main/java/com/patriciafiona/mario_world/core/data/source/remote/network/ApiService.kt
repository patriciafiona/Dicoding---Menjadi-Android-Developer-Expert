package com.patriciafiona.mario_world.core.data.source.remote.network

import com.patriciafiona.mario_world.core.data.source.remote.response.ListCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("JSON/mario_world.json")
    suspend fun getList(): ListCharacterResponse
}
