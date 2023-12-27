package com.patriciafiona.marioworld

import android.app.Application
import com.patriciafiona.mario_world.core.di.databaseModule
import com.patriciafiona.mario_world.core.di.networkModule
import com.patriciafiona.mario_world.core.di.repositoryModule
import com.patriciafiona.marioworld.di.useCaseModule
import com.patriciafiona.marioworld.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}