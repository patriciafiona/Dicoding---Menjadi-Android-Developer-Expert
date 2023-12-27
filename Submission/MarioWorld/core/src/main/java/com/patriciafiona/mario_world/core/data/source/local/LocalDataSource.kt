package com.patriciafiona.mario_world.core.data.source.local

import com.patriciafiona.mario_world.core.data.source.local.entity.CharacterEntity
import com.patriciafiona.mario_world.core.data.source.local.room.MarioDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val marioDao: MarioDao) {

    fun getAllCharacters(): Flow<List<CharacterEntity>> = marioDao.getAllCharacters()

    fun getFavoriteCharacter(): Flow<List<CharacterEntity>> = marioDao.getFavoriteCharacters()

    suspend fun insertCharacter(characterList: List<CharacterEntity>) = marioDao.insertCharacter(characterList)

    fun setFavoriteCharacter(character: CharacterEntity, newState: Boolean) {
        character.isFavorite = newState
        marioDao.updateFavoriteCharacter(character)
    }
}