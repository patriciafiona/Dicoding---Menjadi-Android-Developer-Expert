package com.patriciafiona.marioworld.ui.detail

import androidx.lifecycle.ViewModel
import com.patriciafiona.mario_world.core.domain.model.Character
import com.patriciafiona.mario_world.core.domain.usecase.MarioUseCase

class DetailCharacterViewModel(private val marioUseCase: MarioUseCase) : ViewModel() {
    fun setFavoriteCharacter(character: Character, newStatus:Boolean) =
        marioUseCase.setFavoriteCharacter(character, newStatus)
}

