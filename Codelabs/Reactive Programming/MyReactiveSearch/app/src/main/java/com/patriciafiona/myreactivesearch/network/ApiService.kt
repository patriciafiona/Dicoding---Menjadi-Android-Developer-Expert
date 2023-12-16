package com.patriciafiona.myreactivesearch.network

import com.patriciafiona.myreactivesearch.model.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("{query}.json")
    suspend fun getCountry(
        @Path("query") query: String,
        @Query("key") accessToken: String,
    ): PlaceResponse
}