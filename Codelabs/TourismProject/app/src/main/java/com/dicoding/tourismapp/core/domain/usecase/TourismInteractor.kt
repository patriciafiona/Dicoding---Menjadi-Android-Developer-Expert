package com.dicoding.tourismapp.core.domain.usecase

import com.dicoding.tourismapp.core.data.source.remote.ITourismRepository
import com.dicoding.tourismapp.core.domain.model.Tourism

class TourismInteractor(private val tourismRepository: ITourismRepository): TourismUseCase {

    override fun getAllTourism() = tourismRepository.getAllTourism()

    override fun getFavoriteTourism() = tourismRepository.getFavoriteTourism()

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) = tourismRepository.setFavoriteTourism(tourism, state)
}