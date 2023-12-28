package com.patriciafiona.mario_world.core.di

import androidx.room.Room
import com.patriciafiona.mario_world.core.data.MarioRepository
import com.patriciafiona.mario_world.core.data.source.local.LocalDataSource
import com.patriciafiona.mario_world.core.data.source.local.room.MarioDatabase
import com.patriciafiona.mario_world.core.data.source.remote.RemoteDataSource
import com.patriciafiona.mario_world.core.data.source.remote.network.ApiService
import com.patriciafiona.mario_world.core.domain.repository.IMarioRepository
import com.patriciafiona.mario_world.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MarioDatabase>().marioDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MarioDatabase::class.java, "Mario.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/patriciafiona/patriciafiona.github.io/main/hosting/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMarioRepository> {
        MarioRepository(
            get(),
            get(),
            get()
        )
    }
}