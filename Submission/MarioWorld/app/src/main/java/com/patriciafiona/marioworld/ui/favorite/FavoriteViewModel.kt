package com.patriciafiona.marioworld.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.patriciafiona.mario_world.core.domain.usecase.MarioUseCase

class FavoriteViewModel(private val marioUseCase: MarioUseCase) : ViewModel() {
    val favoriteCharacter = marioUseCase.getFavoriteCharacters().asLiveData()
}

