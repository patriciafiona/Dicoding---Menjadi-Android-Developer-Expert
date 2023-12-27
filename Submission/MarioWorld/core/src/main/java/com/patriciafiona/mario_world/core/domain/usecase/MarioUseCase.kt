package com.patriciafiona.mario_world.core.domain.usecase

import com.patriciafiona.mario_world.core.data.Resource
import com.patriciafiona.mario_world.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface MarioUseCase {
    fun getAllCharacters(): Flow<Resource<List<Character>>>
    fun getFavoriteCharacters(): Flow<List<Character>>
    fun setFavoriteCharacter(character: Character, state: Boolean)
}