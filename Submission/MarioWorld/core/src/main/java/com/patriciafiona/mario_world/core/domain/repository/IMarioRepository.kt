package com.patriciafiona.mario_world.core.domain.repository

import com.patriciafiona.mario_world.core.data.Resource
import kotlinx.coroutines.flow.Flow
import com.patriciafiona.mario_world.core.domain.model.Character

interface IMarioRepository {

    fun getAllCharacters(): Flow<Resource<List<Character>>>

    fun getFavoriteCharacters(): Flow<List<Character>>

    fun setFavoriteCharacter(character: Character, state: Boolean)

}