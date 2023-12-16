package com.dicoding.tourismapp.core.data.source.local

import androidx.lifecycle.LiveData
import com.dicoding.tourismapp.core.data.source.local.entity.TourismEntity
import com.dicoding.tourismapp.core.data.source.local.room.TourismDao
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val tourismDao: TourismDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: TourismDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tourismDao)
            }
    }

    fun getAllTourism(): Flow<List<TourismEntity>> = tourismDao.getAllTourism()

    fun getFavoriteTourism(): Flow<List<TourismEntity>> = tourismDao.getFavoriteTourism()

    suspend fun insertTourism(tourismList: List<TourismEntity>) = tourismDao.insertTourism(tourismList)

    fun setFavoriteTourism(tourism: TourismEntity, newState: Boolean) {
        tourism.isFavorite = newState
        tourismDao.updateFavoriteTourism(tourism)
    }
}