package com.dicoding.tourismapp.core.data.source.remote.network

import com.dicoding.tourismapp.core.data.source.remote.response.ListTourismResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getList(): ListTourismResponse
}